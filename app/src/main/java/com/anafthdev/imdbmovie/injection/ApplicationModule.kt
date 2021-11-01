package com.anafthdev.imdbmovie.injection

import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.DatabaseUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {
	
	@Provides
	@Singleton
	fun provideApplicationContext() = application
	
	@Provides
	@Singleton
	fun providesAppDatastore() = AppDatastore(application)
	
	@Provides
	@Singleton
	fun providesDatabaseUtils() = DatabaseUtils(application)
}