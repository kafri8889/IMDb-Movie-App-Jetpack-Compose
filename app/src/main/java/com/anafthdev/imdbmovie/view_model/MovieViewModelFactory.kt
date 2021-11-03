package com.anafthdev.imdbmovie.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anafthdev.imdbmovie.data.Repository

class MovieViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
	
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return MovieViewModel(repository) as T
	}
}