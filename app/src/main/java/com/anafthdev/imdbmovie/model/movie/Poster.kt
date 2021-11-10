package com.anafthdev.imdbmovie.model.movie

data class Poster(
    val id: String,
    val link: String,
    val aspectRatio: Double,
    val language: String,
    val width: Int,
    val height: Int
) {
     companion object {
         val default = Poster(
             "",
             "",
             0.0,
             "",
             0,
             0
         )
     }
}