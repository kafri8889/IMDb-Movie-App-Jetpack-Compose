package com.anafthdev.imdbmovie.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.anafthdev.imdbmovie.database.box_office_movie.BoxOfficeMovieDatabase
import com.anafthdev.imdbmovie.database.most_popular_movie.MostPopularMovieDatabase
import com.anafthdev.imdbmovie.database.movie.MovieDatabase
import com.anafthdev.imdbmovie.database.top_250_movie.Top250MovieDatabase
import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovie
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.model.top_250_movie.Top250Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class DatabaseUtils @Inject constructor(context: Context) {
	
	private val scope = CoroutineScope(Job() + Dispatchers.IO)
	private val movieDatabase = MovieDatabase.getInstance(context).dao()
	private val mostPopularMovieDatabase = MostPopularMovieDatabase.getInstance(context).dao()
	private val boxOfficeMovieDatabase = BoxOfficeMovieDatabase.getInstance(context).dao()
	private val top250MovieDatabase = Top250MovieDatabase.getInstance(context).dao()
	
	
	
	fun getAllMovies(action: (List<Movie>) -> Unit) {
		val movies = ArrayList<Movie>()
		scope.launch {
			movies.addAll(movieDatabase.getAll())
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(movies)
		} }
	}
	
	fun isMovieExists(id: String, action: (Boolean) -> Unit) {
		var isExists = false
		scope.launch {
			isExists = movieDatabase.isExists(id)
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(isExists)
		} }
	}
	
	fun updateMovie(movie: Movie, action: () -> Unit) = scope.launch {
		movieDatabase.update(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun updateMovie(movies: List<Movie>, action: () -> Unit) = scope.launch {
		movieDatabase.update(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteMovie(movie: Movie, action: () -> Unit) = scope.launch {
		movieDatabase.delete(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteMovie(movies: List<Movie>, action: () -> Unit) = scope.launch {
		movieDatabase.delete(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteAllMovie(action: () -> Unit) = scope.launch {
		movieDatabase.deleteAll()
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post { action() } }
	
	fun insertMovie(movie: Movie, action: () -> Unit) = scope.launch {
		movieDatabase.insert(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun insertMovie(movies: List<Movie>, action: () -> Unit) = scope.launch {
		movieDatabase.insert(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	
	
	fun getAllMostPopularMovie(action: (List<MostPopularMovie>) -> Unit) {
		val movies = ArrayList<MostPopularMovie>()
		scope.launch {
			movies.addAll(mostPopularMovieDatabase.getAll())
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(movies)
		} }
	}
	
	fun isMostPopularMovieExists(movie: MostPopularMovie, action: (Boolean) -> Unit) {
		var isExists = false
		scope.launch {
			isExists = mostPopularMovieDatabase.isExists(movie.id)
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(isExists)
		} }
	}
	
	fun updateMostPopularMovie(movie: MostPopularMovie, action: () -> Unit) = scope.launch {
		mostPopularMovieDatabase.update(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun updateMostPopularMovie(movies: List<MostPopularMovie>, action: () -> Unit) = scope.launch {
		mostPopularMovieDatabase.update(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteMostPopularMovie(movie: MostPopularMovie, action: () -> Unit) = scope.launch {
		mostPopularMovieDatabase.delete(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteMostPopularMovie(movies: List<MostPopularMovie>, action: () -> Unit) = scope.launch {
		mostPopularMovieDatabase.delete(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteAllMostPopularMovie(action: () -> Unit) = scope.launch {
		mostPopularMovieDatabase.deleteAll()
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post { action() } }
	
	fun insertMostPopularMovie(movie: MostPopularMovie, action: () -> Unit) = scope.launch {
		mostPopularMovieDatabase.insert(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun insertMostPopularMovie(movies: List<MostPopularMovie>, action: () -> Unit) = scope.launch {
		mostPopularMovieDatabase.insert(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	
	
	fun getAllBoxOfficeMovie(action: (List<BoxOfficeMovie>) -> Unit) {
		val movies = ArrayList<BoxOfficeMovie>()
		scope.launch {
			movies.addAll(boxOfficeMovieDatabase.getAll())
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(movies)
		} }
	}
	
	fun isBoxOfficeMovieExists(movie: BoxOfficeMovie, action: (Boolean) -> Unit) {
		var isExists = false
		scope.launch {
			isExists = boxOfficeMovieDatabase.isExists(movie.id)
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(isExists)
		} }
	}
	
	fun updateBoxOfficeMovie(movie: BoxOfficeMovie, action: () -> Unit) = scope.launch {
		boxOfficeMovieDatabase.update(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun updateBoxOfficeMovie(movies: List<BoxOfficeMovie>, action: () -> Unit) = scope.launch {
		boxOfficeMovieDatabase.update(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteBoxOfficeMovie(movie: BoxOfficeMovie, action: () -> Unit) = scope.launch {
		boxOfficeMovieDatabase.delete(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteBoxOfficeMovie(movies: List<BoxOfficeMovie>, action: () -> Unit) = scope.launch {
		boxOfficeMovieDatabase.delete(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteAllBoxOfficeMovie(action: () -> Unit) = scope.launch {
		boxOfficeMovieDatabase.deleteAll()
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post { action() } }
	
	fun insertBoxOfficeMovie(movie: BoxOfficeMovie, action: () -> Unit) = scope.launch {
		boxOfficeMovieDatabase.insert(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun insertBoxOfficeMovie(movies: List<BoxOfficeMovie>, action: () -> Unit) = scope.launch {
		boxOfficeMovieDatabase.insert(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	
	
	fun getAllTop250Movie(action: (List<Top250Movie>) -> Unit) {
		val movies = ArrayList<Top250Movie>()
		scope.launch {
			movies.addAll(top250MovieDatabase.getAll())
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(movies)
		} }
	}
	
	fun isTop250MovieExists(movie: Top250Movie, action: (Boolean) -> Unit) {
		var isExists = false
		scope.launch {
			isExists = top250MovieDatabase.isExists(movie.id)
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(isExists)
		} }
	}
	
	fun updateTop250Movie(movie: Top250Movie, action: () -> Unit) = scope.launch {
		top250MovieDatabase.update(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun updateTop250Movie(movies: List<Top250Movie>, action: () -> Unit) = scope.launch {
		top250MovieDatabase.update(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteTop250Movie(movie: Top250Movie, action: () -> Unit) = scope.launch {
		top250MovieDatabase.delete(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteTop250Movie(movies: List<Top250Movie>, action: () -> Unit) = scope.launch {
		top250MovieDatabase.delete(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun deleteAllTop250Movie(action: () -> Unit) = scope.launch {
		top250MovieDatabase.deleteAll()
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post { action() } }
	
	fun insertTop250Movie(movie: Top250Movie, action: () -> Unit) = scope.launch {
		top250MovieDatabase.insert(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun insertTop250Movie(movies: List<Top250Movie>, action: () -> Unit) = scope.launch {
		top250MovieDatabase.insert(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	
	/**
	 * delete the film if a few days have passed
	 */
	fun deleteMovie(movie: Movie, targetDays: Int) {
		if (AppUtils.getFewDaysAheadInMillis(movie.dateCreated, targetDays) <= System.currentTimeMillis()) {
			deleteMovie(movie) { Timber.i("movie deleted: $movie") }
		}
	}
}