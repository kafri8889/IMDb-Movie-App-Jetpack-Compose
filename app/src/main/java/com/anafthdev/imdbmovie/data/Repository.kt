package com.anafthdev.imdbmovie.data

class Repository(
	val localDataSource: LocalDataSource,
	private val remoteDataSource: RemoteDataSource
	) {
	
	fun getMovie(callback: OperationCallback<Any>, apiKey: String, movieType: MovieType, connectedToInternet: Boolean, id: String = "") {
		if (connectedToInternet) {
			if (movieType == MovieType.MOVIE_INFORMATION) {
				localDataSource.isEmpty(MovieType.MOVIE_INFORMATION, id) { exists ->
					// if exists, get movie from localDB, else get from API
					if (exists) localDataSource.getMovie(MovieType.MOVIE_INFORMATION, callback, id)
					else remoteDataSource.getMovie(apiKey, movieType, callback, id)
				}
			} else {
				localDataSource.isEmpty(movieType) { empty ->
					if (empty) remoteDataSource.getMovie(apiKey, movieType, callback)
					else localDataSource.getMovie(movieType, callback)
				}
			}
		} else {
			if (movieType == MovieType.MOVIE_INFORMATION) localDataSource.getMovie(movieType, callback, id)
			else localDataSource.getMovie(movieType, callback)
		}
	}
	
	fun fetchMovie(callback: OperationCallback<Any>, apiKey: String, movieType: MovieType) {
		remoteDataSource.getMovie(apiKey, movieType, callback)
	}
}