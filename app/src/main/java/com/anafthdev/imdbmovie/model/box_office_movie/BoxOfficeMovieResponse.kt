package com.anafthdev.imdbmovie.model.box_office_movie

import com.google.gson.annotations.SerializedName

data class BoxOfficeMovieResponse(
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("items") val boxOfficeMovies: List<BoxOfficeMovie>?
)