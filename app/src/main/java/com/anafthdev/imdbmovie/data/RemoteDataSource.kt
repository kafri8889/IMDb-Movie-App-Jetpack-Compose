package com.anafthdev.imdbmovie.data

import com.anafthdev.imdbmovie.api.APIService
import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovieResponse
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.model.top_250_movie.Top250MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RemoteDataSource {
	
	private val client = APIService.createClient()
	
	fun getMovie(apiKey: String, movieType: MovieType, callback: OperationCallback<Any>, id: String = "") {
		when (movieType) {
			MovieType.MOST_POPULAR_MOVIE -> {
				client.getMostPopularMovies(apiKey).enqueue(object : Callback<MostPopularMovieResponse> {
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
			MovieType.BOX_OFFICE_MOVIE -> {
				client.getBoxOfficeMovies(apiKey).enqueue(object : Callback<BoxOfficeMovieResponse> {
					override fun onResponse(call: Call<BoxOfficeMovieResponse>, response: Response<BoxOfficeMovieResponse>) {
						if (response.isSuccessful) {
							if (response.body() == null) callback.onError("unknown error", null).also {
								Timber.i("unknown error")
							} else {
								if (response.body()!!.boxOfficeMovies == null) {
									if (response.body()!!.errorMessage != null) {
										if (response.body()!!.errorMessage!!.isNotBlank()) {
											callback.onError(response.body()!!.errorMessage!!, null).also {
												Timber.i("response.body()!!.errorMessage!!")
											}
										}
									}
								} else callback.onSuccess(response.body()!!).also { Timber.i("BoxOfficeMovie Retrieved") }
							}
						} else callback.onError(response.errorBody()!!.string(), null).also {
							Timber.i("getBoxOfficeMovie not successfully: ${response.errorBody()!!.string()}")
						}
						
						Timber.i("response status: ${response.code()}")
						Timber.i("response body: ${response.body()}")
					}
					
					override fun onFailure(call: Call<BoxOfficeMovieResponse>, t: Throwable) {
						Timber.i(t, "getBoxOfficeMovie failure: ${t.message}")
						callback.onError("getBoxOfficeMovie failure: ${t.message}", null)
					}
				})
			}
			MovieType.TOP_250_MOVIE -> {
				client.getTop250Movies(apiKey).enqueue(object : Callback<Top250MovieResponse> {
					override fun onResponse(call: Call<Top250MovieResponse>, response: Response<Top250MovieResponse>) {
						if (response.isSuccessful) {
							if (response.body() == null) callback.onError("unknown error", null).also {
								Timber.i("unknown error")
							} else {
								if (response.body()!!.top250Movies == null) {
									if (response.body()!!.errorMessage != null) {
										if (response.body()!!.errorMessage!!.isNotBlank()) {
											callback.onError(response.body()!!.errorMessage!!, null).also {
												Timber.i(response.body()!!.errorMessage!!)
											}
										}
									}
								} else callback.onSuccess(response.body()!!).also { Timber.i("Top250Movie Retrieved") }
							}
						} else callback.onError(response.errorBody()!!.string(), null).also {
							Timber.i("getTop250Movie not successfully: ${response.errorBody()!!.string()}")
						}
						
						Timber.i("response status: ${response.code()}")
						Timber.i("response body: ${response.body()}")
					}
					
					override fun onFailure(call: Call<Top250MovieResponse>, t: Throwable) {
						Timber.i(t, "getTop250Movie failure: ${t.message}")
						callback.onError("getTop250Movie failure: ${t.message}", null)
					}
				})
			}
			MovieType.MOVIE_INFORMATION -> {
				if (id.isBlank()) {
					callback.onError("Movie ID is blank!", null)
					return
				}
				client.getMovie(apiKey, id).enqueue(object : Callback<Movie> {
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
		}
	}
	
	fun cancel() {}
	
}