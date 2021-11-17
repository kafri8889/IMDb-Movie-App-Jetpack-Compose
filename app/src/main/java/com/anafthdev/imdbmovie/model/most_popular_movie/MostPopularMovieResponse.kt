package com.anafthdev.imdbmovie.model.most_popular_movie

import com.google.gson.annotations.SerializedName

data class MostPopularMovieResponse(
    @SerializedName("errorMessage") val errorMessage: String?,
    @SerializedName("items") val mostPopularMovies: List<MostPopularMovie>?
)