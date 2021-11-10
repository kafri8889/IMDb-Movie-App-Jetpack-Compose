package com.anafthdev.imdbmovie.injection

import com.anafthdev.imdbmovie.data.LocalDataSource
import com.anafthdev.imdbmovie.data.RemoteDataSource
import com.anafthdev.imdbmovie.data.Repository
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.DatabaseUtils
import com.anafthdev.imdbmovie.view_model.MovieViewModel
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
	
	@Provides
	@Singleton
	fun providesViewModel() = MovieViewModel(
		application,
		Repository(
			LocalDataSource(providesDatabaseUtils()),
			RemoteDataSource()
		),
		providesAppDatastore()
	)
}