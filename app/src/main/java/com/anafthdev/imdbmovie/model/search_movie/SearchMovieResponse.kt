package com.anafthdev.imdbmovie.model.search_movie

data class SearchMovieResponse(
	val errorMessage: String,
	val expression: String,
	val searchMovies: List<SearchMovie>,
	val searchType: String
)