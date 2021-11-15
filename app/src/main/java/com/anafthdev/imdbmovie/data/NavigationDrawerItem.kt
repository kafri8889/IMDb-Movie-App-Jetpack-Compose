package com.anafthdev.imdbmovie.data

import androidx.annotation.DrawableRes
import com.anafthdev.imdbmovie.R

sealed class NavigationDrawerItem(
	val destination: String,
	val title: String,
	@DrawableRes val icon: Int? = null
) {
	object MostPopularMovies: NavigationDrawerItem(
		NavigationDestination.MOST_POPULAR_MOVIE_SCREEN,
		"Most Popular",
		R.drawable.ic_dot
	)
	
	object BoxOfficeMovie: NavigationDrawerItem(
		NavigationDestination.BOX_OFFICE_MOVIE_SCREEN,
		"Box Office",
		R.drawable.ic_dot
	)
	
	object Top250Movie: NavigationDrawerItem(
		NavigationDestination.TOP_250_MOVIE_SCREEN,
		"Top 250",
		R.drawable.ic_dot
	)
	
	object Settings: NavigationDrawerItem(
		NavigationDestination.SETTINGS_SCREEN,
		"Settings"
	)
	
	companion object {
		val items = listOf(
			MostPopularMovies,
			BoxOfficeMovie,
			Top250Movie,
			Settings
		)
	}
}
