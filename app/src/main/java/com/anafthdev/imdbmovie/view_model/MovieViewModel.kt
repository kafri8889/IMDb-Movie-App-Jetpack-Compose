package com.anafthdev.imdbmovie.view_model

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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val repository: Repository): ViewModel() {
	
	private var _isRefreshing = MutableStateFlow(false)
	private var _mostPopularMovies = MutableLiveData(listOf<MostPopularMovie>())
	
	val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
	val mostPopularMovies: LiveData<List<MostPopularMovie>> = _mostPopularMovies
	
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
							Log.i("MovieViewModel", "_mostPopularMovies: ${_mostPopularMovies.value}")
						}
						
						override fun onError(msg: String) {
							Handler(Looper.getMainLooper()).post {
								Log.i("MovieViewModel", "error: $msg")
							}
						}
					}, MovieType.MOST_POPULAR_MOVIE, isConnected)
				}
				
				viewModelScope.launch {
					_isRefreshing.value = true
					delay(2000)
				}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
					_mostPopularMovies.postValue(listOf(MostPopularMovie.item1, MostPopularMovie.item2))
					Log.i("MovieViewModel", "_mostPopularMovies: ${_mostPopularMovies.value}")
					_isRefreshing.value = false
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
							Log.i("MovieViewModel", "_mostPopularMovies: ${_mostPopularMovies.value}")
						}
						
						override fun onError(msg: String) {
							Handler(Looper.getMainLooper()).post {
								Log.i("MovieViewModel", "error: $msg")
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
	
	companion object {
		const val MOST_POPULAR_MOVIE = "mostPopularMovie"
		const val BOX_OFFICE_MOVIE = "boxOfficeMovie"
		const val TOP_250_MOVIE  = "top250Movie"
		const val MOVIE_INFORMATION = "movieInformation"
	}
}