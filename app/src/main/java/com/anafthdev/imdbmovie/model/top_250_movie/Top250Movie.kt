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
)