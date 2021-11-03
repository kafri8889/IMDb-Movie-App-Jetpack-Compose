package com.anafthdev.imdbmovie.database.top_250_movie

import androidx.room.*
import com.anafthdev.imdbmovie.model.top_250_movie.Top250Movie

@Dao
interface Top250MovieDAO {
	
	@Query("SELECT * FROM top_250_movie_table")
	suspend fun getAll(): List<Top250Movie>
	
	@Query("DELETE FROM top_250_movie_table")
	suspend fun deleteAll()
	
	@Query("SELECT EXISTS (SELECT 1 FROM top_250_movie_table WHERE id LIKE :mID)")
	suspend fun isExists(mID: String): Boolean
	
	@Update
	suspend fun update(movie: Top250Movie)
	
	@Update
	suspend fun update(movies: List<Top250Movie>)
	
	@Delete
	suspend fun delete(movie: Top250Movie)
	
	@Delete
	suspend fun delete(movies: List<Top250Movie>)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movie: Top250Movie)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movies: List<Top250Movie>)
}