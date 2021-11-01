package com.anafthdev.imdbmovie.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anafthdev.imdbmovie.data.NavigationDestination
import com.anafthdev.imdbmovie.injection.Application
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.ui.theme.IMDbMovieTheme
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.DatabaseUtils
import javax.inject.Inject

class MainActivity : ComponentActivity() {
	
	@Inject lateinit var appDatastore: AppDatastore
	@Inject lateinit var databaseUtils: DatabaseUtils
	
	override fun onCreate(savedInstanceState: Bundle?) {
		(applicationContext as Application).appComponent.inject(this)
		super.onCreate(savedInstanceState)
		setContent {
			IMDbMovieTheme {
				MainActivityScreen()
			}
		}
	}
	
	@Composable
	fun MainActivityScreen() {
		val navController = rememberNavController()
		NavHost(
			navController = navController,
			startDestination = NavigationDestination.MOST_POPULAR_MOVIE_SCREEN
		) {
			composable(NavigationDestination.MOST_POPULAR_MOVIE_SCREEN) {
			
			}
			
			composable(NavigationDestination.BOX_OFFICE_MOVIE_SCREEN) {
			
			}
			
			composable(
				"${NavigationDestination.MOVIE_INFORMATION_SCREEN}/?movie={movie}",
				arguments = listOf(
					navArgument("movie") {
						nullable = false
					}
				)
			) { stackEntry ->
				MovieInformationScreen(movie = stackEntry.arguments?.getSerializable("movie") as Movie)
			}
		}
	}
	
	@Composable
	fun MostPopularMovieScreen() {
	
	}
	
	@Composable
	fun BoxOfficeMovieScreen() {
	
	}
	
	@Composable
	fun MovieInformationScreen(movie: Movie) {
	
	}
}
