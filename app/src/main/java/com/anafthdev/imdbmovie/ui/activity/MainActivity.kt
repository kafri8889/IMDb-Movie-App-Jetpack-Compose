package com.anafthdev.imdbmovie.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anafthdev.imdbmovie.R
import com.anafthdev.imdbmovie.data.NavigationDestination
import com.anafthdev.imdbmovie.data.NavigationDrawerItem
import com.anafthdev.imdbmovie.injection.Application
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.ui.*
import com.anafthdev.imdbmovie.ui.theme.IMDbMovieTheme
import com.anafthdev.imdbmovie.ui.theme.default_primary
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.DatabaseUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {
	
	@Inject lateinit var appDatastore: AppDatastore
	@Inject lateinit var databaseUtils: DatabaseUtils
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		(applicationContext as Application).appComponent.inject(this)
		setContent {
			IMDbMovieTheme {
				MainActivityScreen()
			}
		}
	}
	
	@Composable
	fun Navigation(controller: NavHostController) {
		NavHost(
			navController = controller,
			startDestination = NavigationDestination.MOST_POPULAR_MOVIE_SCREEN
		) {
			composable(NavigationDestination.MOST_POPULAR_MOVIE_SCREEN) {
				MostPopularMovieScreen()
			}
			
			composable(NavigationDestination.BOX_OFFICE_MOVIE_SCREEN) {
				BoxOfficeMovieScreen()
			}
			
			composable(NavigationDestination.TOP_250_MOVIE_SCREEN) {
				Top250MovieScreen()
			}
		}
	}
	
	@Composable
	fun MainActivityScreen() {
		val scope = rememberCoroutineScope()
		val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
		val navController = rememberNavController()
		Scaffold(
			scaffoldState = scaffoldState,
			drawerBackgroundColor = Color.White,
			topBar = {
				TopAppBar(
					backgroundColor = default_primary,
					contentColor = Color.White,
					elevation = 8.dp,
					title = { Text(text = stringResource(id = R.string.app_name)) },
					navigationIcon = {
						IconButton(
							onClick = {
								scope.launch {
									scaffoldState.drawerState.open()
								}
							}
						) {
							Image(
								imageVector = Icons.Filled.Menu,
								colorFilter = ColorFilter.tint(Color.White, BlendMode.SrcAtop),
								contentDescription = null
							)
						}
					}
				)
			},
			drawerContent = {
				Drawer(scope = scope, scaffoldState = scaffoldState, navigationController = navController)
			}
		) {
			Navigation(controller = navController)
		}
	}
	
	@Preview(showBackground = true)
	@Composable
	fun MainActivityScreenPreview() {
		val scope = rememberCoroutineScope()
		val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
		val navController = rememberNavController()
		Scaffold(
			drawerBackgroundColor = default_primary,
			topBar = {
				TopAppBar(
					backgroundColor = default_primary,
					contentColor = Color.White,
					elevation = 8.dp,
					title = { Text(text = stringResource(id = R.string.app_name)) },
					navigationIcon = {
						IconButton(
							onClick = {}
						) {
							Image(
								imageVector = Icons.Filled.Menu,
								colorFilter = ColorFilter.tint(Color.White, BlendMode.SrcAtop),
								contentDescription = null
							)
						}
					}
				)
			},
			drawerContent = {
				Drawer(scope = scope, scaffoldState = scaffoldState, navigationController = navController)
			}
		) {
		
		}
	}
}
