package com.anafthdev.imdbmovie.database.box_office_movie

import androidx.room.*
import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovie

@Dao
interface BoxOfficeMovieDAO {
	
	@Query("SELECT * FROM box_office_movie_table")
	suspend fun getAll(): List<BoxOfficeMovie>
	
	@Query("DELETE FROM box_office_movie_table")
	suspend fun deleteAll()
	
	@Query("SELECT EXISTS (SELECT 1 FROM box_office_movie_table WHERE id LIKE :mID)")
	suspend fun isExists(mID: String): Boolean
	
	@Update
	suspend fun update(movie: BoxOfficeMovie)
	
	@Update
	suspend fun update(movies: List<BoxOfficeMovie>)
	
	@Delete
	suspend fun delete(movie: BoxOfficeMovie)
	
	@Delete
	suspend fun delete(movies: List<BoxOfficeMovie>)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movie: BoxOfficeMovie)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movies: List<BoxOfficeMovie>)
}