package com.anafthdev.imdbmovie.model.top_250_movie

import com.google.gson.annotations.SerializedName

data class Top250MovieResponse(
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("items") val top250Movies: List<Top250Movie>?
)