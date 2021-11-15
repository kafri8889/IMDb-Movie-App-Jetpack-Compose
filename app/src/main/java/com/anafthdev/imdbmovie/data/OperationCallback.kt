package com.anafthdev.imdbmovie.data

interface OperationCallback<T> {
	
	fun onSuccess(data: T)
	
	fun onError(msg: String, errorType: Any?)
}