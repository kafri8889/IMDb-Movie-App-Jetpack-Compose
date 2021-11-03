package com.anafthdev.imdbmovie.database.movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anafthdev.imdbmovie.database.DatabaseTypeConverters
import com.anafthdev.imdbmovie.model.movie.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverters::class)
abstract class MovieDatabase: RoomDatabase() {
	
	abstract fun dao(): MovieDAO
	
	companion object {
		private var INSTANCE: MovieDatabase? = null
		
		fun getInstance(base: Context): MovieDatabase {
			if (INSTANCE == null) {
				synchronized(MovieDatabase::class) {
					INSTANCE = Room.databaseBuilder(base, MovieDatabase::class.java, "movie.db")
						.allowMainThreadQueries()
						.build()
				}
			}
			
			return INSTANCE!!
		}
	}
}