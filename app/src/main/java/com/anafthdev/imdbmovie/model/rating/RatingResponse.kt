package com.anafthdev.imdbmovie.model.rating

data class RatingResponse(
	val errorMessage: String,
	val fullTitle: String,
	val imDbId: String,
	val ratings: List<Rating>,
	val title: String,
	val totalRating: String,
	val totalRatingVotes: String,
	val type: String,
	val year: String
)