package com.anafthdev.imdbmovie.model.movie

data class Backdrop(
    val id: String,
    val link: String,
    val aspectRatio: Double,
    val language: String,
    val width: Int,
    val height: Int,
)