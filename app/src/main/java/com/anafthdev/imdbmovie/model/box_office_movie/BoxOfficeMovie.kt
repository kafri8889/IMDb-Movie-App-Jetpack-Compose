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
)