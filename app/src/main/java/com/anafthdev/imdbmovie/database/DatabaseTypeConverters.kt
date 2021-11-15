package com.anafthdev.imdbmovie.database

import androidx.room.TypeConverter
import com.anafthdev.imdbmovie.model.movie.*
import com.google.gson.Gson

object DatabaseTypeConverters {
	
	@TypeConverter
	fun boxOfficeToJSON(boxOffice: BoxOffice) = Gson().toJson(boxOffice)!!
	
	@TypeConverter
	fun boxOfficeFromJSON(boxOfficeJSON: String) = Gson().fromJson(boxOfficeJSON, BoxOffice::class.java)!!
	
	@TypeConverter
	fun writerToJSON(writers: List<Writer>) = Gson().toJson(writers)!!
	
	@TypeConverter
	fun writerFromJSON(similarJSON: String) = Gson().fromJson(similarJSON, Array<Writer>::class.java).toList()
	
	@TypeConverter
	fun similarToJSON(similars: List<Similar>) = Gson().toJson(similars)!!
	
	@TypeConverter
	fun similarFromJSON(similarJSON: String) = Gson().fromJson(similarJSON, Array<Similar>::class.java).toList()
	
	@TypeConverter
	fun starToJSON(stars: List<Star>) = Gson().toJson(stars)!!
	
	@TypeConverter
	fun starFromJSON(starJSON: String) = Gson().fromJson(starJSON, Array<Star>::class.java).toList()
	
	@TypeConverter
	fun actorToJSON(actors: List<Actor>) = Gson().toJson(actors)!!
	
	@TypeConverter
	fun actorFromJSON(actorJSON: String) = Gson().fromJson(actorJSON, Array<Actor>::class.java).toList()
	
	@TypeConverter
	fun companyToJSON(companies: List<Company>) = Gson().toJson(companies)!!
	
	@TypeConverter
	fun companyFromJSON(companyJSON: String) = Gson().fromJson(companyJSON, Array<Company>::class.java).toList()
	
	@TypeConverter
	fun countryToJSON(countries: List<Country>) = Gson().toJson(countries)!!
	
	@TypeConverter
	fun countryFromJSON(countryJSON: String) = Gson().fromJson(countryJSON, Array<Country>::class.java).toList()
	
	@TypeConverter
	fun directorToJSON(directors: List<Director>) = Gson().toJson(directors)!!
	
	@TypeConverter
	fun directorFromJSON(directorJSON: String) = Gson().fromJson(directorJSON, Array<Director>::class.java).toList()
	
	@TypeConverter
	fun genreToJSON(genres: List<Genre>) = Gson().toJson(genres)!!
	
	@TypeConverter
	fun genreFromJSON(genreJSON: String) = Gson().fromJson(genreJSON, Array<Genre>::class.java).toList()
	
	@TypeConverter
	fun keywordToJSON(keywords: List<String>) = Gson().toJson(keywords)!!
	
	@TypeConverter
	fun keywordFromJSON(keywordJSON: String) = Gson().fromJson(keywordJSON, Array<String>::class.java).toList()
	
	@TypeConverter
	fun languageToJSON(languages: List<Language>) = Gson().toJson(languages)!!
	
	@TypeConverter
	fun languageFromJSON(languageJSON: String) = Gson().fromJson(languageJSON, Array<Language>::class.java).toList()
	
	@TypeConverter
	fun postersToJSON(posters: Posters?) = Gson().toJson(posters)!!
	
	@TypeConverter
	fun postersFromJSON(postersJSON: String?): Posters? = Gson().fromJson(postersJSON, Posters::class.java)
	
	@TypeConverter
	fun posterToJSON(posters: List<Poster>) = Gson().toJson(posters)!!
	
	@TypeConverter
	fun posterFromJSON(posterJSON: String) = Gson().fromJson(posterJSON, Array<Poster>::class.java).toList()
	
	@TypeConverter
	fun backdropToJSON(backdrops: List<Backdrop>) = Gson().toJson(backdrops)!!
	
	@TypeConverter
	fun backdropFromJSON(backdropsJSON: String) = Gson().fromJson(backdropsJSON, Array<Backdrop>::class.java).toList()
}