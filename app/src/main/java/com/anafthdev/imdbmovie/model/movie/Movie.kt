package com.anafthdev.imdbmovie.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.anafthdev.imdbmovie.utils.DatabaseUtils

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

    /**
     * date created, serves to delete data if 1 day has passed [DatabaseUtils]
     */
    @ColumnInfo(name = "dateCreated")
    var dateCreated: Long = 0L

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
        
        val item2 = Movie(
            "Won 2 Oscars. Another 97 wins & 156 nominations.",
            "Alcon Entertainment, Columbia Pictures, Sony",
            "R",
            "USA, UK, Canada, Hungary, Spain, Mexico",
            "Denis Villeneuve",
            "",
            "Blade Runner 2049 (2017)",
            "Action, Drama, Mystery, Sci-Fi, Thriller",
            "tt1856101",
            "8.0",
            "474345",
            "https://imdb-api.com/images/original/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_Ratio0.6791_AL_.jpg",
            "menage a trois,female nudity,capitalism,two women one man,threesome",
            "English, Finnish, Japanese, Hungarian, Russian, Somali, Spanish",
            "81",
            "",
            "Thirty years after the events of Blade Runner (1982), a new Blade Runner, L.A.P.D. Officer \\\"K\\\" (Ryan Gosling), unearths a long-buried secret that has the potential to plunge what's left of society into chaos. K's discovery leads him on a quest to find Rick Deckard (Harrison Ford), a former L.A.P.D. Blade Runner, who has been missing for thirty years.",
            "",
            false,
            "2017-10-04",
            "164",
            "2h 44mins",
            "Harrison Ford, Ryan Gosling, Ana de Armas, Dave Bautista",
            "The key to the future is finally unearthed.",
            "Blade Runner 2049",
            "Movie",
            "Hampton Fancher, Michael Green, Philip K. Dick",
            "2017",
            BoxOffice(
                "$150,000,000 (estimated)",
                "$259,304,838",
                "$92,054,159",
                "$32,753,122, 8 October 2017"
            ),
            Posters(
                "",
                "Blade Runner 2049 (2017)",
                "tt1856101",
                "Blade Runner 2049",
                "Movie",
                "2017",
                listOf(
                    Backdrop(
                        "sAtoMqDVhNDQBc3QJL3RF6hlhGq.jpg",
                        "https://imdb-api.com/posters/original/sAtoMqDVhNDQBc3QJL3RF6hlhGq.jpg",
                        1.7777777777777777,
                        "en",
                        3840,
                        2160
                    ),
                    Backdrop(
                        "ilRyazdMJwN05exqhwK4tMKBYZs.jpg",
                        "https://imdb-api.com/posters/original/ilRyazdMJwN05exqhwK4tMKBYZs.jpg",
                        1.7784535186794093,
                        "en",
                        2047,
                        1151
                    ),
                    Backdrop(
                        "8QXGNP0Vb4nsYKub59XpAhiUSQN.jpg",
                        "https://imdb-api.com/posters/original/8QXGNP0Vb4nsYKub59XpAhiUSQN.jpg",
                        1.7777777777777777,
                        "en",
                        1920,
                        1080
                    ),
                    Backdrop(
                        "askFH4GSk2u9z3ZE5ypdKIMeqLJ.jpg",
                        "https://imdb-api.com/posters/original/askFH4GSk2u9z3ZE5ypdKIMeqLJ.jpg",
                        1.7777777777777777,
                        "en",
                        1920,
                        1080
                    ),
                    Backdrop(
                        "bMdSmfI0qwpAkvhAL7sqpjmwgf4.jpg",
                        "https://imdb-api.com/posters/original/bMdSmfI0qwpAkvhAL7sqpjmwgf4.jpg",
                        1.7777777777777777,
                        "en",
                        3840,
                        2160
                    ),
                    Backdrop(
                        "m7ynwXIvSnhxQPR6pOICrC0L2sO.jpg",
                        "https://imdb-api.com/posters/original/m7ynwXIvSnhxQPR6pOICrC0L2sO.jpg",
                        1.7777777777777777,
                        "en",
                        3840,
                        2160
                    ),
                ),
                listOf(
                    Poster(
                        "gajva2L0rPYkEWjzgFlBXCAVBE5.jpg",
                        "https://imdb-api.com/posters/original/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000,
                    ),
                    Poster(
                        "8Yf1LpVhQAn7n2sCsCpDvQFOWw.jpg",
                        "https://imdb-api.com/posters/original/8Yf1LpVhQAn7n2sCsCpDvQFOWw.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000,
                    ),
                    Poster(
                        "jsMVRjLwKWN3gaiGd9pJUrxezsp.jpg",
                        "https://imdb-api.com/posters/original/jsMVRjLwKWN3gaiGd9pJUrxezsp.jpg",
                        0.6748046875,
                        "en",
                        1382,
                        2048,
                    ),
                    Poster(
                        "jLul37v1NcF8XpdSEh4RHsmGocA.jpg",
                        "https://imdb-api.com/posters/original/jLul37v1NcF8XpdSEh4RHsmGocA.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000,
                    ),
                    Poster(
                        "1dw2K2F0WoVxKEbaUJqKF0Gu9vl.jpg",
                        "https://imdb-api.com/posters/original/1dw2K2F0WoVxKEbaUJqKF0Gu9vl.jpg",
                        0.6666666666666666,
                        "en",
                        1000,
                        1500,
                    ),
                    Poster(
                        "5ffpAd9hdNHcYvXeikDt74RxjqM.jpg",
                        "https://imdb-api.com/posters/original/5ffpAd9hdNHcYvXeikDt74RxjqM.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000,
                    ),
                    Poster(
                        "laDkK7or46F7QZvsIQ3CDIAkc23.jpg",
                        "https://imdb-api.com/posters/original/laDkK7or46F7QZvsIQ3CDIAkc23.jpg",
                        0.6666666666666666,
                        "en",
                        2000,
                        3000,
                    ),
                )
            ),
            listOf(
                Writer(
                    "nm0266684",
                    "Hampton Fancher",
                ),
                Writer(
                    "nm0338169",
                    "Michael Green",
                ),
                Writer(
                    "nm0001140",
                    "Philip K. Dick",
                ),
            ),
            listOf(
                Similar(
                    "Ridley Scott",
                    "Blade Runner (1982)",
                    "Action, Sci-Fi, Thriller",
                    "tt0083658",
                    "8.1",
                    "https://imdb-api.com/images/original/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_Ratio0.6737_AL_.jpg",
                    "A blade runner must pursue and terminate four replicants who stole a ship in space, and have returned to Earth to find their creator.",
                    "Harrison Ford, Rutger Hauer, Sean Young",
                    "Blade Runner",
                    "1982",
                ),
                Similar(
                    "Denis Villeneuve",
                    "",
                    "Drama, Sci-Fi",
                    "tt2543164",
                    "7.9",
                    "https://imdb-api.com/images/original/MV5BMTExMzU0ODcxNDheQTJeQWpwZ15BbWU4MDE1OTI4MzAy._V1_Ratio0.6737_AL_.jpg",
                    "A linguist works with the military to communicate with alien lifeforms after twelve mysterious spacecraft appear around the world.",
                    "Amy Adams, Jeremy Renner, Forest Whitaker",
                    "Arrival",
                    "",
                ),
                Similar(
                    "George Miller",
                    "Mad Max: Fury Road (2015)",
                    "Action, Adventure, Sci-Fi",
                    "tt1392190",
                    "8.1",
                    "https://imdb-api.com/images/original/MV5BN2EwM2I5OWMtMGQyMi00Zjg1LWJkNTctZTdjYTA4OGUwZjMyXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_Ratio0.6737_AL_.jpg",
                    "In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.",
                    "Tom Hardy, Charlize Theron, Nicholas Hoult",
                    "Mad Max: Fury Road",
                    "2015",
                ),
                Similar(
                    "Christopher Nolan",
                    "Dunkirk (2017)",
                    "Action, Drama, History",
                    "tt5013056",
                    "7.8",
                    "https://imdb-api.com/images/original/MV5BN2YyZjQ0NTEtNzU5MS00NGZkLTg0MTEtYzJmMWY3MWRhZjM2XkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_Ratio0.6737_AL_.jpg",
                    "Allied soldiers from Belgium, the British Commonwealth and Empire, and France are surrounded by the German Army and evacuated during a fierce battle in World War II.",
                    "Fionn Whitehead, Barry Keoghan, Mark Rylance",
                    "Dunkirk",
                    "2017",
                ),
            ),
            listOf(
                Star(
                    "nm0000148",
                    "Harrison Ford"
                ),
                Star(
                    "nm0331516",
                    "Ryan Gosling"
                ),
                Star(
                    "nm1869101",
                    "Ana de Armas"
                ),
                Star(
                    "nm1176985",
                    "Dave Bautista"
                )
            ),
            listOf(
                Actor(
                    "\'K\'",
                    "nm0331516",
                    "https://imdb-api.com/images/original/MV5BMTQzMjkwNTQ2OF5BMl5BanBnXkFtZTgwNTQ4MTQ4MTE@._V1_Ratio0.7727_AL_.jpg",
                    "Ryan Gosling",
                ),
                Actor(
                    "Sapper Morton",
                    "nm1176985",
                    "https://imdb-api.com/images/original/MV5BNTZkYzU0ZGUtZTk1MC00MzJjLWFmMzItY2M0ODY1ZmI2OGQ0XkEyXkFqcGdeQXVyMjI0MjgwNjc@._V1_Ratio1.7727_AL_.jpg",
                    "Dave Bautista",
                ),
                Actor(
                    "Lieutenant Joshi",
                    "nm0000705",
                    "https://imdb-api.com/images/original/MV5BMTU0NTc4MzEyOV5BMl5BanBnXkFtZTcwODY0ODkzMQ@@._V1_Ratio0.7273_AL_.jpg",
                    "Robin Wright",
                ),
                Actor(
                    "Interviewer",
                    "nm0036553",
                    "https://imdb-api.com/images/original/MV5BMTUzMTYyNzIzOF5BMl5BanBnXkFtZTgwODYwNTEyNjE@._V1_Ratio0.7727_AL_.jpg",
                    "Mark Arnold",
                ),
                Actor(
                    "Angry Old Lady",
                    "nm1315976",
                    "https://imdb-api.com/images/original/nopicture.jpg",
                    "Vilma Szécsi",
                ),
                Actor(
                    "Joi",
                    "nm1869101",
                    "https://imdb-api.com/images/original/MV5BMWM3MDMzNjMtODM5Ny00YmY0LWJhNzQtNTE1ZDNlNjllNDQ0XkEyXkFqcGdeQXVyODkzNTgxMDg@._V1_Ratio1.5000_AL_.jpg",
                    "Ana de Armas",
                ),
            ),
            listOf(
                Company(
                    "co0054452",
                    "Alcon Entertainment"
                ),
                Company(
                    "co0050868",
                    "Columbia Pictures"
                ),
                Company(
                    "co0001799",
                    "Sony"
                ),
            ),
            listOf(
                Country(
                    "USA",
                    "USA",
                ),
                Country(
                    "UK",
                    "UK",
                ),
                Country(
                    "Canada",
                    "Canada",
                ),
                Country(
                    "Hungary",
                    "Hungary",
                ),
                Country(
                    "Spain",
                    "Spain",
                ),
                Country(
                    "Mexico",
                    "Mexico",
                ),
            ),
            listOf(
                Director(
                    "nm0898288",
                    "Denis Villeneuve"
                )
            ),
            listOf(
                Genre(
                    "Action",
                    "Action"
                ),
                Genre(
                    "Drama",
                    "Drama"
                ),
                Genre(
                    "Mystery",
                    "Mystery"
                ),
                Genre(
                    "Sci-Fi",
                    "Sci-Fi"
                ),
                Genre(
                    "Thriller",
                    "Thriller"
                ),
            ),
            listOf(
                "menage a trois",
                "female nudity",
                "capitalism",
                "two women one man",
                "threesome"
            ),
            listOf(
                Language(
                    "English",
                    "English"
                ),
                Language(
                    "Finnish",
                    "Finnish"
                ),
                Language(
                    "Japanese",
                    "Japanese"
                ),
                Language(
                    "Hungarian",
                    "Hungarian"
                ),
                Language(
                    "Russian",
                    "Russian"
                ),
                Language(
                    "Somali",
                    "Somali"
                ),
                Language(
                    "Spanish",
                    "Spanish"
                ),
            )
        )
    }
}