package com.anafthdev.imdbmovie.data

class Repository(
	val localDataSource: LocalDataSource,
	private val remoteDataSource: RemoteDataSource
	) {
	
	fun fetchData(callback: OperationCallback<Any>, movieType: MovieType, connectedToInternet: Boolean) {
		if (connectedToInternet) remoteDataSource.retrieve(callback, movieType)
		else localDataSource.retrieve(callback, movieType)
	}
	
	fun cancelRemote() = remoteDataSource.cancel()
}