package com.anafthdev.imdbmovie.database.most_popular_movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie

@Database(entities = [MostPopularMovie::class], version = 1, exportSchema = false)
abstract class MostPopularMovieDatabase: RoomDatabase() {
	
	abstract fun dao(): MostPopularMovieDAO
	
	companion object {
		private var INSTANCE: MostPopularMovieDatabase? = null
		
		fun getInstance(base: Context): MostPopularMovieDatabase {
			if (INSTANCE == null) {
				synchronized(MostPopularMovieDatabase::class) {
					INSTANCE = Room.databaseBuilder(base, MostPopularMovieDatabase::class.java, "most_popular_movie.db")
						.allowMainThreadQueries()
						.build()
				}
			}
			
			return INSTANCE!!
		}
	}
}