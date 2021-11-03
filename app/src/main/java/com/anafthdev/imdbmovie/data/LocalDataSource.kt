package com.anafthdev.imdbmovie.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.model.top_250_movie.Top250Movie
import com.anafthdev.imdbmovie.utils.DatabaseUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalDataSource(val databaseUtils: DatabaseUtils): DataSource {
	
	override fun retrieve(callback: OperationCallback<Any>, movieType: MovieType) {
		when (movieType) {
			MovieType.MOVIE_INFORMATION -> {
				Log.i("RemoteDataSource", "movie information")
			}
			MovieType.BOX_OFFICE_MOVIE -> {
				Log.i("RemoteDataSource", "box office type")
			}
			MovieType.MOST_POPULAR_MOVIE -> {
				databaseUtils.getAllMostPopularMovie {
					callback.onSuccess(
						MostPopularMovieResponse("", it)
					)
				}
				Log.i("RemoteDataSource", "most popular movie type")
			}
			MovieType.TOP_250_MOVIE -> {
				Log.i("RemoteDataSource", "top 250 movie type")
			}
		}
	}
	
	override fun cancel() {}
}