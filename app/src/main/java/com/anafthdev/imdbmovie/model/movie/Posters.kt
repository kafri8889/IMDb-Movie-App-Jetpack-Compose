package com.anafthdev.imdbmovie.model.movie

data class Posters(
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val title: String,
    val type: String,
    val year: String,
    val backdrops: List<Backdrop>,
    val posters: List<Poster>
) {
    companion object {
        val default = Posters(
            "",
            "",
            "",
            "",
            "",
            "",
            emptyList(),
            emptyList()
        )
    }
}