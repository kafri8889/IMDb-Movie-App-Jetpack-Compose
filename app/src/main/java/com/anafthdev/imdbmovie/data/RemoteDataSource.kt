package com.anafthdev.imdbmovie.data

import com.anafthdev.imdbmovie.api.APIService
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RemoteDataSource: DataSource {
	
	private val apiClient = APIService.createClient()
	
	fun getMovie(id: String, apiKey: String, callback: OperationCallback<Any>) {
		apiClient.getMovie(apiKey, id).enqueue(object : Callback<Movie> {
			override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
				if (response.isSuccessful) {
					if (response.body() == null) callback.onError("null response, is your api key correct?").also {
						Timber.i("null response, is your api key correct?")
					} else callback.onSuccess(response.body()!!)
				} else callback.onError(response.errorBody()!!.string()).also {
					Timber.i("getMovie not successfully: ${response.errorBody()!!.string()}")
				}
				
				Timber.i("response status: ${response.code()}")
				Timber.i("response body: ${response.body()}")
			}
			
			override fun onFailure(call: Call<Movie>, t: Throwable) {
				Timber.i(t, "getMovie failure: ${t.message}")
			}
		})
	}
	
	override fun retrieve(callback: OperationCallback<Any>, movieType: MovieType) {
		when (movieType) {
			MovieType.MOVIE_INFORMATION -> {
				Timber.i("RemoteDataSource", "movie information")
			}
			MovieType.BOX_OFFICE_MOVIE -> {
				Timber.i("RemoteDataSource", "box office type")
			}
			MovieType.MOST_POPULAR_MOVIE -> {
				callback.onSuccess(
					MostPopularMovieResponse("", listOf(MostPopularMovie.item1))
				)
				Timber.i("RemoteDataSource", "most popular movie type")
			}
			MovieType.TOP_250_MOVIE -> {
				Timber.i("RemoteDataSource", "top 250 movie type")
			}
		}
	}
	
	override fun cancel() {
	
	}
}