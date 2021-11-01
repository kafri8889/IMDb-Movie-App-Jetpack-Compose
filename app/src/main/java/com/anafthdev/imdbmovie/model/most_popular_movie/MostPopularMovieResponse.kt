package com.anafthdev.imdbmovie.model.most_popular_movie

data class MostPopularMovieResponse(
    val errorMessage: String,
    val mostPopularMovies: List<MostPopularMovie>
)