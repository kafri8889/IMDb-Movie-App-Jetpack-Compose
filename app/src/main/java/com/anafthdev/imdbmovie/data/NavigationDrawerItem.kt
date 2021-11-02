package com.anafthdev.imdbmovie.data

import androidx.annotation.DrawableRes
import com.anafthdev.imdbmovie.R

sealed class NavigationDrawerItem(
	val destination: String,
	val title: String,
	@DrawableRes val icon: Int
) {
	object MostPopularMovies: NavigationDrawerItem(
		NavigationDestination.MOST_POPULAR_MOVIE_SCREEN,
		"Most Popular Movie",
		R.drawable.ic_dot
	)
	
	object BoxOfficeMovie: NavigationDrawerItem(
		NavigationDestination.BOX_OFFICE_MOVIE_SCREEN,
		"Box Office Movie",
		R.drawable.ic_dot
	)
	
	object Top250Movie: NavigationDrawerItem(
		NavigationDestination.TOP_250_MOVIE_SCREEN,
		"Top 250 Movie",
		R.drawable.ic_dot
	)
	
	companion object {
		val items = listOf(
			MostPopularMovies,
			BoxOfficeMovie,
			Top250Movie
		)
	}
}
