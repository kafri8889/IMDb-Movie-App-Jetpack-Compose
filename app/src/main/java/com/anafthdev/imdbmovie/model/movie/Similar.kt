package com.anafthdev.imdbmovie.model.movie

data class Similar(
    val directors: String,
    val fullTitle: String,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val image: String,
    val plot: String,
    val stars: String,
    val title: String,
    val year: String
)