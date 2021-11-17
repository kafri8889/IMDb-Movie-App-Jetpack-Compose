package com.anafthdev.imdbmovie.model.box_office_movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "box_office_movie_table")
data class BoxOfficeMovie(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "gross") val gross: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "rank") val rank: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "weekend") val weekend: String,
    @ColumnInfo(name = "weeks") val weeks: String
) {
    companion object {
        val item1 = BoxOfficeMovie(
            "tt9032400",
            "$118.1M",
            "https://m.media-amazon.com/images/M/MV5BMTExZmVjY2ItYTAzYi00MDdlLWFlOWItNTJhMDRjMzQ5ZGY0XkEyXkFqcGdeQXVyODIyOTEyMzY@._V1_UX128_CR0,3,128,176_AL_.jpg",
            "1",
            "Eternals",
            "$26.9M",
            "2",
        )
    
        val item2 = BoxOfficeMovie(
            "tt2397461",
            "$22.2M",
            "https://m.media-amazon.com/images/M/MV5BZGIxYTU5MzctY2MzNS00MTRhLWEwM2UtY2Q5Mzk3OTAzMzcwXkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_UX128_CR0,3,128,176_AL_.jpg",
            "2",
            "Clifford the Big Red Dog",
            "$16.6M",
            "1",
        )
    
        val item3 = BoxOfficeMovie(
            "tt1160419",
            "$93.2M",
            "https://m.media-amazon.com/images/M/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX128_CR0,3,128,176_AL_.jpg",
            "3",
            "Dune: Part One",
            "$5.5M",
            "4",
        )
    }
}