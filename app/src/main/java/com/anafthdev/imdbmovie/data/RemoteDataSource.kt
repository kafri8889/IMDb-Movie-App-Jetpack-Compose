package com.anafthdev.imdbmovie.data

import android.util.Log
import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovieResponse
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import retrofit2.Call
import java.lang.RuntimeException

class RemoteDataSource: DataSource {
	
	val mostPopularMovieCall: Call<MostPopularMovieResponse>? = null
	val boxOfficeMovieCall: Call<BoxOfficeMovieResponse>? = null
	val movieCall: Call<Movie>? = null
	
	override fun retrieve(callback: OperationCallback<Any>, movieType: MovieType) {
		when (movieType) {
			MovieType.MOVIE_INFORMATION -> {
				Log.i("RemoteDataSource", "movie information")
			}
			MovieType.BOX_OFFICE_MOVIE -> {
				Log.i("RemoteDataSource", "box office type")
			}
			MovieType.MOST_POPULAR_MOVIE -> {
				callback.onSuccess(
					MostPopularMovieResponse("", listOf(MostPopularMovie.item1))
				)
				Log.i("RemoteDataSource", "most popular movie type")
			}
			MovieType.TOP_250_MOVIE -> {
				Log.i("RemoteDataSource", "top 250 movie type")
			}
		}
	}
	
	override fun cancel() {
	
	}
}