package com.anafthdev.imdbmovie.database.box_office_movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovie

@Database(entities = [BoxOfficeMovie::class], version = 1, exportSchema = false)
abstract class BoxOfficeMovieDatabase: RoomDatabase() {
	
	abstract fun dao(): BoxOfficeMovieDAO
	
	companion object {
		private var INSTANCE: BoxOfficeMovieDatabase? = null
		
		fun getInstance(base: Context): BoxOfficeMovieDatabase {
			if (INSTANCE == null) {
				synchronized(BoxOfficeMovieDatabase::class) {
					INSTANCE = Room.databaseBuilder(base, BoxOfficeMovieDatabase::class.java, "box_office_movie.db")
						.allowMainThreadQueries()
						.build()
				}
			}
			
			return INSTANCE!!
		}
	}
}