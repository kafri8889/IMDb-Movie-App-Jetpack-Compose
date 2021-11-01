package com.anafthdev.imdbmovie.database

import androidx.room.*
import com.anafthdev.imdbmovie.model.movie.Movie

@Dao
interface MovieDAO {
	
	@Query("SELECT * FROM movie_table")
	suspend fun getAll(): List<Movie>
	
	@Query("SELECT EXISTS (SELECT 1 FROM movie_table WHERE id LIKE :mID)")
	suspend fun isExists(mID: String): Boolean
	
	@Update
	suspend fun update(movie: Movie)
	
	@Update
	suspend fun update(movies: List<Movie>)
	
	@Delete
	suspend fun delete(movie: Movie)
	
	@Delete
	suspend fun delete(movies: List<Movie>)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movie: Movie)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movies: List<Movie>)
}