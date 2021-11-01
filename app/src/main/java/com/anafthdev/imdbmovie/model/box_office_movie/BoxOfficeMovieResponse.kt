package com.anafthdev.imdbmovie.model.box_office_movie

data class BoxOfficeMovieResponse(
    val errorMessage: String,
    val boxOfficeMovies: List<BoxOfficeMovie>
)