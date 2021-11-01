package com.anafthdev.imdbmovie.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie_table")
data class Movie(
    
    @ColumnInfo(name = "awards")
    @SerializedName("awards")
    val awards: String,

    @ColumnInfo(name = "companies")
    @SerializedName("companies")
    val companies: String,

    @ColumnInfo(name = "contentRating")
    @SerializedName("contentRating")
    val contentRating: String,

    @ColumnInfo(name = "countries")
    @SerializedName("countries")
    val countries: String,

    @ColumnInfo(name = "directors")
    @SerializedName("directors")
    val directors: String,

    @ColumnInfo(name = "errorMessage")
    @SerializedName("errorMessage")
    val errorMessage: String,
    
    @ColumnInfo(name = "fullTitle")
    @SerializedName("fullTitle")
    val fullTitle: String,

    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres: String,

    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "imDbRating")
    @SerializedName("imDbRating")
    val imDbRating: String,

    @ColumnInfo(name = "imDbRatingVotes")
    @SerializedName("imDbRatingVotes")
    val imDbRatingVotes: String,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String,

    @ColumnInfo(name = "keywords")
    @SerializedName("keywords")
    val keywords: String,

    @ColumnInfo(name = "languages")
    @SerializedName("languages")
    val languages: String,

    @ColumnInfo(name = "metacriticRating")
    @SerializedName("metacriticRating")
    val metacriticRating: String,

    @ColumnInfo(name = "originalTitle")
    @SerializedName("originalTitle")
    val originalTitle: String,

    @ColumnInfo(name = "plot")
    @SerializedName("plot")
    val plot: String,

    @ColumnInfo(name = "plotLocal")
    @SerializedName("plotLocal")
    val plotLocal: String,

    @ColumnInfo(name = "plotLocalIsRtl")
    @SerializedName("plotLocalIsRtl")
    val plotLocalIsRtl: Boolean,

    @ColumnInfo(name = "releaseDate")
    @SerializedName("releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "runtimeMins")
    @SerializedName("runtimeMins")
    val runtimeMins: String,

    @ColumnInfo(name = "runtimeStr")
    @SerializedName("runtimeStr")
    val runtimeStr: String,
    
    @ColumnInfo(name = "stars")
    @SerializedName("stars")
    val stars: String,

    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    val tagline: String,
    
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String,

    @ColumnInfo(name = "writers")
    @SerializedName("writers")
    val writers: String,

    @ColumnInfo(name = "year")
    @SerializedName("year")
    val year: String,

    @ColumnInfo(name = "boxOffice")
    @SerializedName("boxOffice")
    val boxOffice: BoxOffice,

    @ColumnInfo(name = "writerList")
    @SerializedName("writerList")
    val writerList: List<Writer>,

    @ColumnInfo(name = "similars")
    @SerializedName("similars")
    val similars: List<Similar>,

    @ColumnInfo(name = "starList")
    @SerializedName("starList")
    val starList: List<Star>,

    @ColumnInfo(name = "actorList")
    @SerializedName("actorList")
    val actorList: List<Actor>,

    @ColumnInfo(name = "companyList")
    @SerializedName("companyList")
    val companyList: List<Company>,

    @ColumnInfo(name = "countryList")
    @SerializedName("countryList")
    val countryList: List<Country>,

    @ColumnInfo(name = "directorList")
    @SerializedName("directorList")
    val directorList: List<Director>,

    @ColumnInfo(name = "genreList")
    @SerializedName("genreList")
    val genreList: List<Genre>,

    @ColumnInfo(name = "keywordList")
    @SerializedName("keywordList")
    val keywordList: List<String>,

    @ColumnInfo(name = "languageList")
    @SerializedName("languageList")
    val languageList: List<Language>,

//    @SerializedName("fullCast")
//    val fullCast: Any?,
//
//    @SerializedName("posters")
//    val posters: Any?,
//
//    @SerializedName("ratings")
//    val ratings: Any?,
//
//    @SerializedName("trailer")
//    val trailer: Any?,
//
//    @SerializedName("tvEpisodeInfo")
//    val tvEpisodeInfo: Any?,
//
//    @SerializedName("tvSeriesInfo")
//    val tvSeriesInfo: Any?,
//
//    @SerializedName("wikipedia")
//    val wikipedia: Any?,
//
//    @SerializedName("images")
//    val images: Any?,
): Serializable