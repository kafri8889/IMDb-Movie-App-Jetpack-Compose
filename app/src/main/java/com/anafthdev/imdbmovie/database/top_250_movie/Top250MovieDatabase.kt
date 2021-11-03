package com.anafthdev.imdbmovie.database.top_250_movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anafthdev.imdbmovie.model.top_250_movie.Top250Movie

@Database(entities = [Top250Movie::class], version = 1, exportSchema = false)
abstract class Top250MovieDatabase: RoomDatabase() {
	
	abstract fun dao(): Top250MovieDAO
	
	companion object {
		private var INSTANCE: Top250MovieDatabase? = null
		
		fun getInstance(base: Context): Top250MovieDatabase {
			if (INSTANCE == null) {
				synchronized(Top250MovieDatabase::class) {
					INSTANCE = Room.databaseBuilder(base, Top250MovieDatabase::class.java, "top_250_movie.db")
						.allowMainThreadQueries()
						.build()
				}
			}
			
			return INSTANCE!!
		}
	}
}