package com.anafthdev.imdbmovie.view_model

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.imdbmovie.data.LocalDataSource
import com.anafthdev.imdbmovie.data.MovieType
import com.anafthdev.imdbmovie.data.OperationCallback
import com.anafthdev.imdbmovie.data.Repository
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.AppUtils.toast
import com.anafthdev.imdbmovie.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MovieViewModel @Inject constructor(
	private val application: Application,
	private val repository: Repository,
	val appDatastore: AppDatastore,
	val networkUtil: NetworkUtil
	): ViewModel() {
	
	private var _isRefreshing = MutableStateFlow(false)
	private var _mostPopularMovies = MutableLiveData(listOf<MostPopularMovie>())
	private var _currentMovie = MutableLiveData(Movie.default)
	
	val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
	val mostPopularMovies: LiveData<List<MostPopularMovie>> = _mostPopularMovies
	val currentMovie: LiveData<Movie> = _currentMovie
	
	fun refresh(type: MovieType) {
		val isConnectedToInternet = networkUtil.isNetworkAvailable.value
		if (isConnectedToInternet) {
			when (type) {
				MovieType.MOST_POPULAR_MOVIE -> {
					viewModelScope.launch {
						_isRefreshing.value = true
						appDatastore.getApiKey.collect { apiKey ->
							repository.refreshMovie(object : OperationCallback<Any> {
								override fun onSuccess(data: Any) {
									data as MostPopularMovieResponse
									
									repository.localDataSource.databaseUtils.deleteAllMostPopularMovie {
										repository.localDataSource.databaseUtils.insertMostPopularMovie(data.mostPopularMovies) {}
									}
									
									_mostPopularMovies.value = data.mostPopularMovies
									_isRefreshing.value = false
									Timber.i("_mostPopularMovies: ${_mostPopularMovies.value}")
								}
								
								override fun onError(msg: String, errorType: Any?) {
									Handler(Looper.getMainLooper()).post {
										Timber.i("error: $msg")
									}
								}
							}, apiKey, MovieType.MOST_POPULAR_MOVIE)
						}
					}
				}
				MovieType.BOX_OFFICE_MOVIE -> {}
				MovieType.TOP_250_MOVIE -> {}
				MovieType.MOVIE_INFORMATION -> {}
			}
		} else "No Internet Connection".toast(application)
	}
	
	fun get(type: MovieType) {
		
		// work for prevent auto-calling when isUseSampleData or apiKey value changes
		var execute = true
		
		val isConnectedToInternet = networkUtil.isNetworkAvailable.value
		
		when (type) {
			MovieType.MOST_POPULAR_MOVIE -> {
				viewModelScope.launch {
					appDatastore.isUseSampleData.collect { isUseSampleData ->
						appDatastore.getApiKey.collect { apiKey ->
							if (!isUseSampleData) {
								if (execute) {
									execute = false
									repository.getMovie(object : OperationCallback<Any> {
										override fun onSuccess(data: Any) {
											data as MostPopularMovieResponse
											
											if (isConnectedToInternet) {
												repository.localDataSource.databaseUtils.deleteAllMostPopularMovie {
													repository.localDataSource.databaseUtils.insertMostPopularMovie(data.mostPopularMovies!!) {}
												}
											}
											
											_mostPopularMovies.value = data.mostPopularMovies
											Timber.i("_mostPopularMovies: ${_mostPopularMovies.value}")
										}
										
										override fun onError(msg: String, errorType: Any?) {
											Handler(Looper.getMainLooper()).post {
												Timber.i("error: $msg")
											}
										}
									}, apiKey, MovieType.MOST_POPULAR_MOVIE, isConnectedToInternet)
								}
							} else { Handler(Looper.getMainLooper()).post { _mostPopularMovies.value = listOf(MostPopularMovie.item1) } }
						}
					}
				}
			}
			MovieType.BOX_OFFICE_MOVIE -> {}
			MovieType.TOP_250_MOVIE -> {}
			MovieType.MOVIE_INFORMATION -> {}
		}
	}
	
	/**
	 * @param id movie ID
	 */
	fun getMovie(id: String) {
		
		// work for prevent auto-calling when isUseSampleData or apiKey value changes
		var execute = true

		val isConnectedToInternet = networkUtil.isNetworkAvailable.value
		
		Timber.i("request to get movie with id: $id...")
		viewModelScope.launch(Dispatchers.IO) {
			appDatastore.isUseSampleData.collect { isUseSampleData ->
				appDatastore.getApiKey.collect { apiKey ->
					if (!isUseSampleData) {
						if (execute) {
							execute = false
							repository.getMovie(id, apiKey, isConnectedToInternet, object : OperationCallback<Any> {
								override fun onSuccess(data: Any) {
									Handler(Looper.getMainLooper()).post {
										data as Movie
										
										var isError = false
										if (data.errorMessage != null) {
											if (data.errorMessage.isNotBlank()) {
												isError = true
												data.errorMessage.toast(application)
												Timber.i("error: ${data.errorMessage}")
											}
										}
										
										if (!isError) {
											data.dateCreated = System.currentTimeMillis()
											repository.localDataSource.databaseUtils.insertMovie(data) {}
										}
										
										_currentMovie.value = data
										Timber.i("_currentMovie: $data")
									}
								}
								
								override fun onError(msg: String, errorType: Any?) {
									if (errorType != null) {
										errorType as String
										when (errorType) {
											LocalDataSource.NO_MOVIE_WITH_ID -> "No Internet Connection".toast(application)
										}
									}
									
									msg.toast(application)
									Timber.i("error: $msg")
								}
							})
						}
					} else { Handler(Looper.getMainLooper()).post { _currentMovie.value = Movie.item2 } }
				}
			}
		}
	}
}