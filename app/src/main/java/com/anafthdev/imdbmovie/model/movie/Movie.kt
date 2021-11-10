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
    val awards: String?,

    @ColumnInfo(name = "companies")
    @SerializedName("companies")
    val companies: String?,

    @ColumnInfo(name = "contentRating")
    @SerializedName("contentRating")
    val contentRating: String?,

    @ColumnInfo(name = "countries")
    @SerializedName("countries")
    val countries: String?,

    @ColumnInfo(name = "directors")
    @SerializedName("directors")
    val directors: String?,

    @ColumnInfo(name = "errorMessage")
    @SerializedName("errorMessage")
    val errorMessage: String?,
    
    @ColumnInfo(name = "fullTitle")
    @SerializedName("fullTitle")
    val fullTitle: String?,

    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres: String?,

    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "imDbRating")
    @SerializedName("imDbRating")
    val imDbRating: String?,

    @ColumnInfo(name = "imDbRatingVotes")
    @SerializedName("imDbRatingVotes")
    val imDbRatingVotes: String?,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String?,

    @ColumnInfo(name = "keywords")
    @SerializedName("keywords")
    val keywords: String?,

    @ColumnInfo(name = "languages")
    @SerializedName("languages")
    val languages: String?,

    @ColumnInfo(name = "metacriticRating")
    @SerializedName("metacriticRating")
    val metacriticRating: String?,

    @ColumnInfo(name = "originalTitle")
    @SerializedName("originalTitle")
    val originalTitle: String?,

    @ColumnInfo(name = "plot")
    @SerializedName("plot")
    val plot: String?,

    @ColumnInfo(name = "plotLocal")
    @SerializedName("plotLocal")
    val plotLocal: String?,

    @ColumnInfo(name = "plotLocalIsRtl")
    @SerializedName("plotLocalIsRtl")
    val plotLocalIsRtl: Boolean?,

    @ColumnInfo(name = "releaseDate")
    @SerializedName("releaseDate")
    val releaseDate: String?,

    @ColumnInfo(name = "runtimeMins")
    @SerializedName("runtimeMins")
    val runtimeMins: String?,

    @ColumnInfo(name = "runtimeStr")
    @SerializedName("runtimeStr")
    val runtimeStr: String?,
    
    @ColumnInfo(name = "stars")
    @SerializedName("stars")
    val stars: String?,

    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    val tagline: String?,
    
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String?,

    @ColumnInfo(name = "writers")
    @SerializedName("writers")
    val writers: String?,

    @ColumnInfo(name = "year")
    @SerializedName("year")
    val year: String?,

    @ColumnInfo(name = "boxOffice")
    @SerializedName("boxOffice")
    val boxOffice: BoxOffice?,

    @ColumnInfo(name = "posters")
    @SerializedName("posters")
    val posters: Posters?,

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
): Serializable {
    
    companion object {
        val default = Movie(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
        )
        
        val item1 = Movie(
            "Top rated movie #13 | Won 4 Oscars157 wins & 220 nominations total",
            "Warner Bros., Legendary Entertainment, Syncopy",
            "12",
            "USA, UK",
            "Christopher Nolan",
            "",
            "Inception (2010)",
            "Action, Adventure, Sci-Fi",
            "tt1375666",
            "8.8",
            "2144730",
            "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6762_AL_.jpg",
            "dream,ambiguous ending,subconscious,surprise ending,mindbender",
            "English, Japanese, French",
            "74",
            "",
            "Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction, stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable. Cobb&#39;s rare ability has made him a coveted player in this treacherous new world of corporate espionage, but it has also made him an international fugitive and cost him everything he has ever loved. Now Cobb is being offered a chance at redemption. One last job could give him his life back but only if he can accomplish the impossible, inception. Instead of the perfect heist, Cobb and his team of specialists have to pull off the reverse: their task is not to steal an idea, but to plant one. If they succeed, it could be the perfect crime. But no amount of careful planning or expertise can prepare the team for the dangerous enemy that seems to predict their every move. An enemy that only Cobb could have seen coming.",
            "",
            false,
            "2010-07-29",
            "148",
            "2h 28min",
            "Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page",
            "Your mind is the scene of the crime",
            "Inception",
            "Movie",
            "Christopher Nolan",
            "2010",
            BoxOffice(
                "$160,000,000 (estimated)",
                "$62,785,337",
                "$292,576,195",
                "$62,785,337"
            ),
            Posters(
                "",
                "Inception (2010)",
                "tt1375666",
                "Inception",
                "Movie",
                "2010",
                listOf(
                    Backdrop(
                        "s3TBrRGB1iav7gFOCNx3H31MoES.jpg",
                        "https://imdb-api.com/posters/original/s3TBrRGB1iav7gFOCNx3H31MoES.jpg",
                        1.7777777777777777,
                        "en",
                        1920,
                        1080
                    ),
                    Backdrop(
                        "gqby0RhyehP3uRrzmdyUZ0CgPPe.jpg",
                        "https://imdb-api.com/posters/original/gqby0RhyehP3uRrzmdyUZ0CgPPe.jpg",
                        1.7777777777777777,
                        "en",
                        1920,
                        1080
                    ),
                    Backdrop(
                        "7dW4BobKP5ijWCLnbhxvcogVvHs.jpg",
                        "https://imdb-api.com/posters/original/7dW4BobKP5ijWCLnbhxvcogVvHs.jpg",
                        1.7777777777777777,
                        "en",
                        1920,
                        1080
                    ),
                    Backdrop(
                        "DaNRMZskVejsye2msqqwDuyOjk.jpg",
                        "https://imdb-api.com/posters/original/DaNRMZskVejsye2msqqwDuyOjk.jpg",
                        1.7777777777777777,
                        "en",
                        1920,
                        1080
                    ),
                    Backdrop(
                        "ii8QGacT3MXESqBckQlyrATY0lT.jpg",
                        "https://imdb-api.com/posters/original/ii8QGacT3MXESqBckQlyrATY0lT.jpg",
                        1.7777777777777777,
                        "en",
                        1920,
                        1080
                    ),
                    Backdrop(
                        "2HmLvOvu1rhfxK50WfJ4jFKy9zQ.jpg",
                        "https://imdb-api.com/posters/original/2HmLvOvu1rhfxK50WfJ4jFKy9zQ.jpg",
                        1.7777777777777777,
                        "en",
                        1920,
                        1080
                    ),
                ),
                listOf(
                    Poster(
                        "9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
                        "https://imdb-api.com/posters/original/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000
                    ),
                    Poster(
                        "edv5CZvWj09upOsy2Y6IwDhK8bt.jpg",
                        "https://imdb-api.com/posters/original/edv5CZvWj09upOsy2Y6IwDhK8bt.jpg",
                        0.6666666666666666,
                        "en",
                        1000,
                        1500
                    ),
                    Poster(
                        "zt5kihG59UaOYyGcXnBz3HwQxXl.jpg",
                        "https://imdb-api.com/posters/original/zt5kihG59UaOYyGcXnBz3HwQxXl.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000
                    ),
                    Poster(
                        "eM2Y0RN3mUwp40Nm9I22Slgzl4c.jpg",
                        "https://imdb-api.com/posters/original/eM2Y0RN3mUwp40Nm9I22Slgzl4c.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000
                    ),
                    Poster(
                        "oV5yEdlu1PodRNTyp8d2nvk2qOy.jpg",
                        "https://imdb-api.com/posters/original/oV5yEdlu1PodRNTyp8d2nvk2qOy.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000
                    ),
                    Poster(
                        "nzJxStuBY1TBH0O2IdIrLQSTaFX.jpg",
                        "https://imdb-api.com/posters/original/nzJxStuBY1TBH0O2IdIrLQSTaFX.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000
                    ),
                )
            ),
            listOf(
                Writer(
                    "nm0634240",
                    "Christopher Nolan"
                )
            ),
            listOf(
                Similar(
                    "",
                    "Interstellar",
                    "",
                    "tt0816692",
                    "8.6",
                    "https://imdb-api.com/images/original/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6763_AL_.jpg",
                    "",
                    "",
                    "Interstellar",
                    ""
                ),
                Similar(
                    "",
                    "The Dark Knight",
                    "",
                    "tt0468569",
                    "9.0",
                    "https://imdb-api.com/images/original/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_Ratio0.6763_AL_.jpg",
                    "",
                    "",
                    "The Dark Knight",
                    ""
                ),
                Similar(
                    "",
                    "Fight Club",
                    "",
                    "tt0137523",
                    "8.8",
                    "https://imdb-api.com/images/original/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_Ratio0.6763_AL_.jpg",
                    "",
                    "",
                    "Fight Club",
                    ""
                ),
                Similar(
                    "",
                    "Forrest Gump",
                    "",
                    "tt0109830",
                    "8.8",
                    "https://imdb-api.com/images/original/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_Ratio0.6957_AL_.jpg",
                    "",
                    "",
                    "Forrest Gump",
                    ""
                ),
            ),
            listOf(
                Star(
                    "nm0000138",
                    "Leonardo DiCaprio"
                ),
                Star(
                    "nm0330687",
                    "Joseph Gordon-Levitt"
                ),
                Star(
                    "nm0000138",
                    "Elliot Page"
                ),
            ),
            listOf(
                Actor(
                    "Cobbas Cobb",
                    "nm0000138",
                    "https://imdb-api.com/images/original/MV5BMjI0MTg3MzI0M15BMl5BanBnXkFtZTcwMzQyODU2Mw@@._V1_Ratio1.0000_AL_.jpg",
                    "Leonardo DiCaprio"
                ),
                Actor(
                    "Joseph Gordon-Levitt",
                    "nm0330687",
                    "https://imdb-api.com/images/original/MV5BMTY3NTk0NDI3Ml5BMl5BanBnXkFtZTgwNDA3NjY0MjE@._V1_Ratio1.0000_AL_.jpg",
                    "Arthuras Arthur"
                ),
                Actor(
                    "Ariadneas Ariadne",
                    "nm0000138",
                    "https://imdb-api.com/images/original/MV5BMjI0MTg3MzI0M15BMl5BanBnXkFtZTcwMzQyODU2Mw@@._V1_Ratio1.0000_AL_.jpg",
                    "Elliot Page"
                ),
                Actor(
                    "Saitoas Saito",
                    "nm0913822",
                    "https://imdb-api.com/images/original/MV5BMTQzMTUzNjc4Nl5BMl5BanBnXkFtZTcwMTUyODU2Mw@@._V1_Ratio1.0000_AL_.jpg",
                    "Ken Watanabe"
                ),
            ),
            listOf(
                Company(
                    "co0002663",
                    "Warner Bros."
                ),
                Company(
                    "co0159111",
                    "Legendary Entertainment"
                ),
                Company(
                    "co0147954",
                    "Syncopy"
                ),
            ),
            listOf(
                Country(
                    "USA",
                    "USA"
                ),
                Country(
                    "UK",
                    "UK"
                ),
            ),
            listOf(
                Director(
                    "nm0634240",
                    "Christopher Nolan",
                )
            ),
            listOf(
                Genre(
                    "Action",
                    "Action"
                ),
                Genre(
                    "Adventure",
                    "Adventure"
                ),
                Genre(
                    "Sci-Fi",
                    "Sci-Fi"
                )
            ),
            listOf(
                "dream",
                "ambiguous ending",
                "subconscious",
                "surprise ending",
                "mindbender"
            ),
            listOf(
                Language(
                    "English",
                    "English"
                ),
                Language(
                    "Japanese",
                    "Japanese"
                ),
                Language(
                    "French",
                    "French"
                )
            )
        )
    }
}