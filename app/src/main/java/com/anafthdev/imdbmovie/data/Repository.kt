package com.anafthdev.imdbmovie.data

class Repository(
	val localDataSource: LocalDataSource,
	private val remoteDataSource: RemoteDataSource
	) {
	
	fun getMovie(callback: OperationCallback<Any>, apiKey: String, movieType: MovieType, connectedToInternet: Boolean) {
		if (connectedToInternet) when (movieType) {
			MovieType.MOST_POPULAR_MOVIE -> {
				localDataSource.databaseUtils.getAllMostPopularMovie {
					if (it.isEmpty()) remoteDataSource.getMovie(apiKey, movieType, callback)
					else localDataSource.getMovie(movieType, callback)
				}
			}
			MovieType.TOP_250_MOVIE -> {}
			MovieType.BOX_OFFICE_MOVIE -> {}
			MovieType.MOVIE_INFORMATION -> {}
		}
		else localDataSource.getMovie(movieType, callback)
	}
	
	fun getMovie(
		id: String,
		apiKey: String,
		connectedToInternet: Boolean,
		callback: OperationCallback<Any>
	) {
		if (connectedToInternet) localDataSource.checkMovie(id) { exists ->
			// if exists, get movie from localDB, else get from API
			if (exists) { localDataSource.getMovie(MovieType.MOVIE_INFORMATION, callback, id) }
			else remoteDataSource.getMovie(id, apiKey, callback)
		} else localDataSource.getMovie(MovieType.MOVIE_INFORMATION, callback, id)
	}
	
	fun refreshMovie(callback: OperationCallback<Any>, apiKey: String, movieType: MovieType) {
		remoteDataSource.getMovie(apiKey, movieType, callback)
	}
	
	fun cancelRemote() = remoteDataSource.cancel()
}