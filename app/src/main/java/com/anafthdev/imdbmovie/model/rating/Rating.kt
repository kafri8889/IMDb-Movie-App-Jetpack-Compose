package com.anafthdev.imdbmovie.model.rating

data class Rating(
    val percent: String,
    val rating: String,  // 1 to 10
    val votes: String
)