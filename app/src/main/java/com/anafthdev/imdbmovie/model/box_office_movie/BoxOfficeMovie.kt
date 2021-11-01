package com.anafthdev.imdbmovie.model.box_office_movie

data class BoxOfficeMovie(
    val gross: String,
    val id: String,
    val image: String,
    val rank: String,
    val title: String,
    val weekend: String,
    val weeks: String
)