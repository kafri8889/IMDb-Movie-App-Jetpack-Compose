package com.anafthdev.imdbmovie.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.anafthdev.imdbmovie.database.MovieDatabase
import com.anafthdev.imdbmovie.model.movie.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class DatabaseUtils @Inject constructor(context: Context) {
	
	private val scope = CoroutineScope(Job() + Dispatchers.IO)
	private val movieDatabase = MovieDatabase.getInstance(context).dao()
	
	fun getAllMovies(action: (List<Movie>) -> Unit) {
		val movies = ArrayList<Movie>()
		scope.launch {
			movies.addAll(movieDatabase.getAll())
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(movies)
		} }
	}
	
	fun isExists(movie: Movie, action: (Boolean) -> Unit) {
		var isExists = false
		scope.launch {
			isExists = movieDatabase.isExists(movie.id)
		}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
			action(isExists)
		} }
	}
	
	fun update(movie: Movie, action: () -> Unit) = scope.launch {
		movieDatabase.update(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun update(movies: List<Movie>, action: () -> Unit) = scope.launch {
		movieDatabase.update(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun delete(movie: Movie, action: () -> Unit) = scope.launch {
		movieDatabase.delete(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun delete(movies: List<Movie>, action: () -> Unit) = scope.launch {
		movieDatabase.delete(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun insert(movie: Movie, action: () -> Unit) = scope.launch {
		movieDatabase.insert(movie)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
	
	fun insert(movies: List<Movie>, action: () -> Unit) = scope.launch {
		movieDatabase.insert(movies)
	}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
		action()
	} }
}