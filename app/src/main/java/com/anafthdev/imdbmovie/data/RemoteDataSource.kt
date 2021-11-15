package com.anafthdev.imdbmovie.data

import com.anafthdev.imdbmovie.api.APIService
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RemoteDataSource {
	
	private val apiClient = APIService.createClient()
	
	private var callGetMovie: Call<Movie>? = null
	private var callGetMostPopularMovie: Call<MostPopularMovieResponse>? = null
	
	fun getMovie(id: String, apiKey: String, callback: OperationCallback<Any>) {
		callGetMovie = APIService.createClient().getMovie(apiKey, id)
		callGetMovie!!.enqueue(object : Callback<Movie> {
			override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
				if (response.isSuccessful) {
					if (response.body() == null) callback.onError("unknown error", null).also {
						Timber.i("unknown error")
					} else {
						if (response.body()!!.title == null) {
							if (response.body()!!.errorMessage != null) {
								if (response.body()!!.errorMessage!!.isNotBlank()) {
									callback.onError("null body, is your api key correct?", null).also {
										Timber.i("null body, is your api key correct?")
									}
								}
							}
						} else callback.onSuccess(response.body()!!).also { Timber.i("Movie Retrieved") }
					}
				} else callback.onError(response.errorBody()!!.string(), null).also {
					Timber.i("getMovie not successfully: ${response.errorBody()!!.string()}")
				}
				
				Timber.i("response status: ${response.code()}")
				Timber.i("response body: ${response.body()}")
			}
			
			override fun onFailure(call: Call<Movie>, t: Throwable) {
				Timber.i(t, "getMovie failure: ${t.message}")
				callback.onError("getMovie failure: ${t.message}", null)
			}
		})
	}
	
	fun getMovie(apiKey: String, movieType: MovieType, callback: OperationCallback<Any>) {
		when (movieType) {
			MovieType.MOST_POPULAR_MOVIE -> {
				callGetMostPopularMovie = APIService.createClient().getMostPopularMovies(apiKey)
				callGetMostPopularMovie!!.enqueue(object : Callback<MostPopularMovieResponse> {
					override fun onResponse(call: Call<MostPopularMovieResponse>, response: Response<MostPopularMovieResponse>) {
						if (response.isSuccessful) {
							if (response.body() == null) callback.onError("unknown error", null).also {
								Timber.i("unknown error")
							} else {
								if (response.body()!!.mostPopularMovies == null) {
									if (response.body()!!.errorMessage != null) {
										if (response.body()!!.errorMessage!!.isNotBlank()) {
											callback.onError(response.body()!!.errorMessage!!, null).also {
												Timber.i("response.body()!!.errorMessage!!")
											}
										}
									}
								} else callback.onSuccess(response.body()!!).also { Timber.i("MostPopularMovie Retrieved") }
							}
						} else callback.onError(response.errorBody()!!.string(), null).also {
							Timber.i("getMostPopularMovie not successfully: ${response.errorBody()!!.string()}")
						}
						
						Timber.i("response status: ${response.code()}")
						Timber.i("response body: ${response.body()}")
					}
					
					override fun onFailure(call: Call<MostPopularMovieResponse>, t: Throwable) {
						Timber.i(t, "getMostPopularMovie failure: ${t.message}")
						callback.onError("getMostPopularMovie failure: ${t.message}", null)
					}
				})
			}
			MovieType.TOP_250_MOVIE -> {
			
			}
			MovieType.BOX_OFFICE_MOVIE -> {
			
			}
			MovieType.MOVIE_INFORMATION -> {
			
			}
		}
	}
	
	fun cancel() {}
	
}