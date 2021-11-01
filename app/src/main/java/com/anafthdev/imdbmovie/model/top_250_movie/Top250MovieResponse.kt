package com.anafthdev.imdbmovie.model.top_250_movie

data class Top250MovieResponse(
    val errorMessage: String,
    val top250Movies: List<Top250Movie>
)