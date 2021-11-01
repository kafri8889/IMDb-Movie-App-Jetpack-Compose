package com.anafthdev.imdbmovie.injection

import android.app.Application

class Application: Application() {

	val appComponent = DaggerApplicationComponent
		.builder()
		.applicationModule(ApplicationModule(this))
		.build()
}