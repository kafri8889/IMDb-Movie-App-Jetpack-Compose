package com.anafthdev.imdbmovie.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIService {
	
	fun createClient(): APIClient {
		val client = OkHttpClient.Builder()
			.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
			.connectTimeout(30L, TimeUnit.SECONDS)
			.readTimeout(30L, TimeUnit.SECONDS)
			.writeTimeout(30L, TimeUnit.SECONDS)
			.build()
		
		return Retrofit.Builder()
			.baseUrl("https://imdb-api.com/")
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build().create(APIClient::class.java)
	}
}