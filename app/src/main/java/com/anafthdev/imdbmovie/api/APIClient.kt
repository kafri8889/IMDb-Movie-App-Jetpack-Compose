package com.anafthdev.imdbmovie.api

import com.anafthdev.imdbmovie.model.box_office_movie.BoxOfficeMovieResponse
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovieResponse
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.model.search_movie.SearchMovieResponse
import com.anafthdev.imdbmovie.model.top_250_movie.Top250MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient {
	
	@GET("/en/API/Title/{apiKey}/{movieID}/")
	fun getMovie(
		@Path("apiKey") apiKey: String,
		@Path("movieID") movieID: String
	): Call<Movie>
	
	@GET("/en/API/MostPopularMovies/{apiKey}/")
	fun getMostPopularMovies(
		@Path("apiKey") apiKey: String
	): Call<MostPopularMovieResponse>
	
	@GET("/en/API/BoxOffice/{apiKey}/")
	fun getBoxOfficeMovies(
		@Path("apiKey") apiKey: String
	): Call<BoxOfficeMovieResponse>
	
	@GET("/en/API/Top250Movies/{apiKey}/")
	fun getTop250Movies(
		@Path("apiKey") apiKey: String
	): Call<Top250MovieResponse>
	
	@GET("/en/API/SearchMovie/{apiKey}/{query}/")
	fun searchMovie(
		@Path("apiKey") apiKey: String,
		@Path("query") query: String
	): Call<SearchMovieResponse>
	
}