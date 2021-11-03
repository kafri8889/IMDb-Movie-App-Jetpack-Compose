package com.anafthdev.imdbmovie.data

interface DataSource {
	
	fun retrieve(callback: OperationCallback<Any>, movieType: MovieType)
	
	fun cancel()
}