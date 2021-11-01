package com.anafthdev.imdbmovie.injection

import com.anafthdev.imdbmovie.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		ApplicationModule::class
	]
)
interface ApplicationComponent {
	
	fun inject(context: MainActivity)
}