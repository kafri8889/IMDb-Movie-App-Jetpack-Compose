package com.anafthdev.imdbmovie.database.most_popular_movie

import androidx.room.*
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie

@Dao
interface MostPopularMovieDAO {
	
	@Query("SELECT * FROM most_popular_movie_table")
	suspend fun getAll(): List<MostPopularMovie>
	
	@Query("DELETE FROM most_popular_movie_table")
	suspend fun deleteAll()
	
	@Query("SELECT EXISTS (SELECT 1 FROM most_popular_movie_table WHERE id LIKE :mID)")
	suspend fun isExists(mID: String): Boolean
	
	@Update
	suspend fun update(movie: MostPopularMovie)
	
	@Update
	suspend fun update(movies: List<MostPopularMovie>)
	
	@Delete
	suspend fun delete(movie: MostPopularMovie)
	
	@Delete
	suspend fun delete(movies: List<MostPopularMovie>)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movie: MostPopularMovie)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movies: List<MostPopularMovie>)
}