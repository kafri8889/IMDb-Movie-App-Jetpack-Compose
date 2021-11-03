package com.anafthdev.imdbmovie.model.most_popular_movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "most_popular_movie_table")
data class MostPopularMovie(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "crew") val crew: String,
    @ColumnInfo(name = "fullTitle") val fullTitle: String,
    @ColumnInfo(name = "imDbRating") val imDbRating: String,
    @ColumnInfo(name = "imDbRatingCount") val imDbRatingCount: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "rank") val rank: String,
    @ColumnInfo(name = "rankUpDown") val rankUpDown: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String
) {
    companion object {
        val item1 = MostPopularMovie(
            "tt1160419",
            "Denis Villeneuve (dir.), Timothée Chalamet, Rebecca Ferguson",
            "Dune (2021)",
            "8.3",
            "251274",
            "https://imdb-api.com/images/original/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_Ratio0.6716_AL_.jpg",
            "1",
            "0",
            "Dune",
            "2021"
        )
    
        val item2 = MostPopularMovie(
            "tt9032400",
            "Chloé Zhao (dir.), Gemma Chan, Richard Madden",
            "Eternals (2021)",
            "",
            "0",
            "https://imdb-api.com/images/original/MV5BMTExZmVjY2ItYTAzYi00MDdlLWFlOWItNTJhMDRjMzQ5ZGY0XkEyXkFqcGdeQXVyODIyOTEyMzY@._V1_Ratio0.6716_AL_.jpg",
            "3",
            "+6",
            "Eternals",
            "2021"
        )
    }
}