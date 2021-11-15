package com.anafthdev.imdbmovie.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIService {
	
	private const val BASE_URL = "https://imdb-api.com/"
	const val POSTER_URL = "https://image.tmdb.org/t/p/w300/"
	
	fun createClient(): APIClient {
		val client = OkHttpClient.Builder()
			.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
			.connectTimeout(30L, TimeUnit.SECONDS)
			.readTimeout(30L, TimeUnit.SECONDS)
			.writeTimeout(30L, TimeUnit.SECONDS)
			.build()
		
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build().create(APIClient::class.java)
	}
}