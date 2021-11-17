package com.anafthdev.imdbmovie.model.top_250_movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_250_movie_table")
data class Top250Movie(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "crew") val crew: String,
    @ColumnInfo(name = "fullTitle") val fullTitle: String,
    @ColumnInfo(name = "imDbRating") val imDbRating: String,
    @ColumnInfo(name = "imDbRatingCount") val imDbRatingCount: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "rank") val rank: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String
) {
    companion object {
        val item1 = Top250Movie(
            "tt0111161",
            "Frank Darabont (dir.), Tim Robbins, Morgan Freeman",
            "The Shawshank Redemption (1994)",
            "9.2",
            "2488165",
            "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,3,128,176_AL_.jpg",
            "1",
            "The Shawshank Redemption",
            "1994",
        )
        
        val item2 = Top250Movie(
            "tt0068646",
            "Francis Ford Coppola (dir.), Marlon Brando, Al Pacino",
            "The Godfather (1972)",
            "9.1",
            "1717236",
            "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,1,128,176_AL_.jpg",
            "2",
            "The Godfather",
            "1972",
        )
    }
}