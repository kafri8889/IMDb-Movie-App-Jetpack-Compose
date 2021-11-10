package com.anafthdev.imdbmovie.view_model

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.imdbmovie.data.MovieType
import com.anafthdev.imdbmovie.data.OperationCallback
import com.anafthdev.imdbmovie.data.Repository
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.AppUtils.toast
import kotlinx.coroutines.delay
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
	private val appDatastore: AppDatastore
	): ViewModel() {
	
	private var _isRefreshing = MutableStateFlow(false)
	private var _mostPopularMovies = MutableLiveData(listOf<MostPopularMovie>())
	
	private var _currentMovieID = MutableStateFlow("")
	private var _currentMovie = MutableLiveData(Movie.default)
	
	val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
	val mostPopularMovies: LiveData<List<MostPopularMovie>> = _mostPopularMovies
	
	val currentMovieID: StateFlow<String> = _currentMovieID.asStateFlow()
	val currentMovie: LiveData<Movie> = _currentMovie
	
	fun setMovieID(id: String) = viewModelScope.launch { _currentMovieID.emit(id) }
	
	fun refresh(type: String, isConnected: Boolean) {
		when (type) {
			MOST_POPULAR_MOVIE -> {
				viewModelScope.launch {
					repository.fetchData(object : OperationCallback<Any> {
						override fun onSuccess(data: Any) {
							data as MostPopularMovieResponse
							val testData = ArrayList(data.mostPopularMovies).apply { add(MostPopularMovie.item2) }
							if (isConnected) {
								repository.localDataSource.databaseUtils.deleteAllMostPopularMovie {
									repository.localDataSource.databaseUtils.insertMostPopularMovie(testData) {}
								}
							}
							
							_mostPopularMovies.postValue(testData)
							Timber.i("_mostPopularMovies: ${_mostPopularMovies.value}")
						}
						
						override fun onError(msg: String) {
							Handler(Looper.getMainLooper()).post {
								Timber.i("error: $msg")
							}
						}
					}, MovieType.MOST_POPULAR_MOVIE, isConnected)
				}
				
				viewModelScope.launch {
					_isRefreshing.emit(true)
					delay(2000)
					_isRefreshing.emit(false)
				}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
					_mostPopularMovies.postValue(listOf(MostPopularMovie.item1, MostPopularMovie.item2))
					Timber.i("_mostPopularMovies: ${_mostPopularMovies.value}")
				} }
			}
			BOX_OFFICE_MOVIE -> {}
			TOP_250_MOVIE -> {}
			MOVIE_INFORMATION -> {}
		}
	}
	
	fun get(type: String, isConnected: Boolean) {
		when (type) {
			MOST_POPULAR_MOVIE -> {
				viewModelScope.launch {
					repository.fetchData(object : OperationCallback<Any> {
						override fun onSuccess(data: Any) {
							data as MostPopularMovieResponse
							if (isConnected) {
								repository.localDataSource.databaseUtils.deleteAllMostPopularMovie {
									repository.localDataSource.databaseUtils.insertMostPopularMovie(data.mostPopularMovies) {}
								}
							}
							
							_mostPopularMovies.postValue(data.mostPopularMovies)
							Timber.i("_mostPopularMovies: ${_mostPopularMovies.value}")
						}
						
						override fun onError(msg: String) {
							Handler(Looper.getMainLooper()).post {
								Timber.i("error: $msg")
							}
						}
					}, MovieType.MOST_POPULAR_MOVIE, isConnected)
				}
			}
			BOX_OFFICE_MOVIE -> {}
			TOP_250_MOVIE -> {}
			MOVIE_INFORMATION -> {}
		}
	}
	
	fun getMovie(id: String) {
		Timber.i("request to get movie with id: $id...")
		viewModelScope.launch {
			appDatastore.getApiKey.collect { apiKey ->
				repository.getMovie(id, apiKey, object : OperationCallback<Any> {
					override fun onSuccess(data: Any) {
						Handler(Looper.getMainLooper()).post {
							data as Movie
							if (data.errorMessage != null) {
								data.errorMessage.toast(application)
								Timber.i("error: ${data.errorMessage}")
							} else _currentMovie.value = data
							Timber.i("_currentMovie: $data")
						}
					}
					
					override fun onError(msg: String) {
						Timber.i("error: $msg")
					}
				})
			}
		}
	}
	
	companion object {
		const val MOST_POPULAR_MOVIE = "mostPopularMovie"
		const val BOX_OFFICE_MOVIE = "boxOfficeMovie"
		const val TOP_250_MOVIE  = "top250Movie"
		const val MOVIE_INFORMATION = "movieInformation"
	}
}