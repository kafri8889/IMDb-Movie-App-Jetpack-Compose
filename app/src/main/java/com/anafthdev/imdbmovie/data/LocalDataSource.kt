package com.anafthdev.imdbmovie.data

import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovieResponse
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.top_250_movie.Top250MovieResponse
import com.anafthdev.imdbmovie.utils.AppUtils.get
import com.anafthdev.imdbmovie.utils.DatabaseUtils

class LocalDataSource(val databaseUtils: DatabaseUtils) {
	
	fun getMovie(
		movieType: MovieType,
		callback: OperationCallback<Any>,
		id: String = ""
	) {
		when (movieType) {
			MovieType.MOST_POPULAR_MOVIE -> {
				databaseUtils.getAllMostPopularMovie {
					callback.onSuccess(MostPopularMovieResponse("", it))
				}
			}
			MovieType.TOP_250_MOVIE -> {
				databaseUtils.getAllTop250Movie {
					callback.onSuccess(Top250MovieResponse("", it))
				}
			}
			MovieType.BOX_OFFICE_MOVIE -> {
				databaseUtils.getAllBoxOfficeMovie {
					callback.onSuccess(BoxOfficeMovieResponse("", it))
				}
			}
			MovieType.MOVIE_INFORMATION -> {
				databaseUtils.getAllMovies { list ->
					val movie = list.get { it.id == id }
					if (movie != null) callback.onSuccess(movie)
					else callback.onError("no movie with id: $id", NO_MOVIE_WITH_ID)
				}
			}
		}
	}
	
	fun checkMovie(id: String, action: (Boolean) -> Unit) {
		databaseUtils.isMovieExists(id, action)
	}
	
	companion object {
		const val NO_MOVIE_WITH_ID = "movie_not_exists_in_local_db"
	}
}