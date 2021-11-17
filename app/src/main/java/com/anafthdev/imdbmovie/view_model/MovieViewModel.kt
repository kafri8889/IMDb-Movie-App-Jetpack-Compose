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
import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovie
import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovieResponse
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.model.top_250_movie.Top250Movie
import com.anafthdev.imdbmovie.model.top_250_movie.Top250MovieResponse
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.AppUtils.toast
import com.anafthdev.imdbmovie.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
	private var _boxOfficeMovies = MutableLiveData(listOf<BoxOfficeMovie>())
	private var _top250Movies = MutableLiveData(listOf<Top250Movie>())
	private var _currentMovie = MutableLiveData(Movie.default)
	
	val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
	val mostPopularMovies: LiveData<List<MostPopularMovie>> = _mostPopularMovies
	val boxOfficeMovies: LiveData<List<BoxOfficeMovie>> = _boxOfficeMovies
	val top250Movies: LiveData<List<Top250Movie>> = _top250Movies
	val currentMovie: LiveData<Movie> = _currentMovie
	
	
	/**
	 * @param type movie type, see [MovieType]
	 */
	fun refresh(type: MovieType) {
		val isConnectedToInternet = networkUtil.isNetworkAvailable.value
		if (isConnectedToInternet) {
			viewModelScope.launch {
				_isRefreshing.value = true
				appDatastore.getApiKey.collect { apiKey ->
					repository.fetchMovie(object : OperationCallback<Any> {
						override fun onSuccess(data: Any) {
							when (type) {
								MovieType.MOST_POPULAR_MOVIE -> {
									data as MostPopularMovieResponse
									
									repository.localDataSource.databaseUtils.deleteAllMostPopularMovie {
										repository.localDataSource.databaseUtils.insertMostPopularMovie(data.mostPopularMovies!!) {}
									}
									
									_mostPopularMovies.value = data.mostPopularMovies
									Timber.i("_boxOfficeMovies: ${_mostPopularMovies.value}")
								}
								MovieType.BOX_OFFICE_MOVIE -> {
									data as BoxOfficeMovieResponse
									
									repository.localDataSource.databaseUtils.deleteAllBoxOfficeMovie {
										repository.localDataSource.databaseUtils.insertBoxOfficeMovie(data.boxOfficeMovies!!) {}
									}
									
									_boxOfficeMovies.value = data.boxOfficeMovies
									Timber.i("_boxOfficeMovies: ${_boxOfficeMovies.value}")
								}
								MovieType.TOP_250_MOVIE -> {
									data as Top250MovieResponse
									
									repository.localDataSource.databaseUtils.deleteAllTop250Movie {
										repository.localDataSource.databaseUtils.insertTop250Movie(data.top250Movies!!) {}
									}
									
									_top250Movies.value = data.top250Movies
									Timber.i("_top250Movies: ${_top250Movies.value}")
								}
								MovieType.MOVIE_INFORMATION -> {}
							}
							
							_isRefreshing.value = false
						}
						
						override fun onError(msg: String, errorType: Any?) {
							Handler(Looper.getMainLooper()).post {
								Timber.i("error: $msg")
							}
						}
					}, apiKey, type)
				}
			}
		} else "No Internet Connection".toast(application)
	}
	
	/**
	 * @param id movie ID
	 * @param type movie type, see [MovieType]
	 */
	fun get(type: MovieType, id: String = "") {
		
		// work for prevent auto-calling when isUseSampleData or apiKey value changes
		var execute = true
		
		val isConnectedToInternet = networkUtil.isNetworkAvailable.value
		
		viewModelScope.launch {
			appDatastore.isUseSampleData.collect { isUseSampleData ->
				appDatastore.getApiKey.collect { apiKey ->
					if (!isUseSampleData) {
						if (execute) {
							execute = false
							repository.getMovie(object : OperationCallback<Any> {
								override fun onSuccess(data: Any) {
									when (type) {
										MovieType.MOST_POPULAR_MOVIE -> {
											if (isConnectedToInternet) {
												data as MostPopularMovieResponse
												
												repository.localDataSource.databaseUtils.deleteAllMostPopularMovie {
													repository.localDataSource.databaseUtils.insertMostPopularMovie(data.mostPopularMovies!!) {}
												}
												
												_mostPopularMovies.value = data.mostPopularMovies
												Timber.i("_boxOfficeMovies: ${_mostPopularMovies.value}")
											}
										}
										MovieType.BOX_OFFICE_MOVIE -> {
											if (isConnectedToInternet) {
												data as BoxOfficeMovieResponse
												
												repository.localDataSource.databaseUtils.deleteAllBoxOfficeMovie {
													repository.localDataSource.databaseUtils.insertBoxOfficeMovie(data.boxOfficeMovies!!) {}
												}
												
												_boxOfficeMovies.value = data.boxOfficeMovies
												Timber.i("_boxOfficeMovies: ${_boxOfficeMovies.value}")
											}
										}
										MovieType.TOP_250_MOVIE -> {
											if (isConnectedToInternet) {
												data as Top250MovieResponse
												
												repository.localDataSource.databaseUtils.deleteAllTop250Movie {
													repository.localDataSource.databaseUtils.insertTop250Movie(data.top250Movies!!) {}
												}
												
												_top250Movies.value = data.top250Movies
												Timber.i("_top250Movies: ${_top250Movies.value}")
											}
										}
										MovieType.MOVIE_INFORMATION -> {
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
								}
								
								override fun onError(msg: String, errorType: Any?) {
									Handler(Looper.getMainLooper()).post {
										if (errorType != null) {
											errorType as String
											when (errorType) {
												LocalDataSource.NO_MOVIE_WITH_ID -> "No Internet Connection".toast(application)
											}
										}
										
										Timber.i("error: $msg")
									}
								}
							}, apiKey, type, isConnectedToInternet, if (id.isNotBlank()) id else "")
						}
					} else { Handler(Looper.getMainLooper()).post {
						when (type) {
							MovieType.MOST_POPULAR_MOVIE -> _mostPopularMovies.value = listOf(MostPopularMovie.item1, MostPopularMovie.item2)
							MovieType.BOX_OFFICE_MOVIE -> _boxOfficeMovies.value = listOf(BoxOfficeMovie.item1, BoxOfficeMovie.item2, BoxOfficeMovie.item3)
							MovieType.TOP_250_MOVIE -> _top250Movies.value = listOf(Top250Movie.item1, Top250Movie.item2)
							MovieType.MOVIE_INFORMATION -> _currentMovie.value = Movie.item1
						}
					} }
				}
			}
		}
	}
}