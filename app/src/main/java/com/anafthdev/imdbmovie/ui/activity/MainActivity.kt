package com.anafthdev.imdbmovie.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anafthdev.imdbmovie.BuildConfig
import com.anafthdev.imdbmovie.R
import com.anafthdev.imdbmovie.data.NavigationDestination
import com.anafthdev.imdbmovie.data.NavigationDrawerItem
import com.anafthdev.imdbmovie.injection.Application
import com.anafthdev.imdbmovie.ui.*
import com.anafthdev.imdbmovie.ui.theme.IMDbMovieTheme
import com.anafthdev.imdbmovie.ui.theme.default_primary
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.DatabaseUtils
import com.anafthdev.imdbmovie.view_model.MovieViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainActivity : ComponentActivity() {
	
	@Inject lateinit var appDatastore: AppDatastore
	@Inject lateinit var databaseUtils: DatabaseUtils
	@Inject lateinit var viewModel: MovieViewModel
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		(applicationContext as Application).appComponent.inject(this)
		if (BuildConfig.DEBUG) Timber.plant(object : Timber.DebugTree() {
			override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
				super.log(priority, "DEBUG_$tag", message, t)
			}
		})
		
		databaseUtils.getAllMovies { movies ->
			movies.forEach {
				databaseUtils.deleteMovie(it, 1)
			}
		}
		
		setContent {
			IMDbMovieTheme {
				MainActivityScreen()
			}
		}
	}
	
	@OptIn(ExperimentalAnimationApi::class)
	@Composable
	fun MainActivityScreen() {
		val scope = rememberCoroutineScope()
		val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
		val navController = rememberNavController()
		val density = LocalDensity.current
		var showNavRail by remember { mutableStateOf(false) }
		
		Scaffold(
			scaffoldState = scaffoldState,
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
									showNavRail = !showNavRail
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
		) {
			Box {
				val navigationBackStackEntry by navController.currentBackStackEntryAsState()
				val currentRoute = navigationBackStackEntry?.destination?.route
				NavHost(
					navController = navController,
					startDestination = NavigationDestination.MOST_POPULAR_MOVIE_SCREEN
				) {
					composable(
						route = "${NavigationDestination.MOVIE_INFORMATION_SCREEN}/{movieID}",
						arguments = listOf(
							navArgument("movieID") {
								type = NavType.StringType
							}
						)
					) { entry ->
						val movieID = entry.arguments?.getString("movieID")
						MovieInformationScreen(
							navigationController = navController,
							viewModel = viewModel,
							movieID = movieID ?: ""
						)
					}
					
					composable(NavigationDestination.MOST_POPULAR_MOVIE_SCREEN) {
						MostPopularMovieScreen(
							navController,
							viewModel
						)
					}
					
					composable(NavigationDestination.BOX_OFFICE_MOVIE_SCREEN) {
						BoxOfficeMovieScreen(
							navController,
							viewModel
						)
					}
					
					composable(NavigationDestination.TOP_250_MOVIE_SCREEN) {
						Top250MovieScreen(
							navController,
							viewModel
						)
					}
					
					composable(NavigationDestination.SETTINGS_SCREEN) {
						SettingsScreen(appDatastore, scope)
					}
				}
				
				AnimatedVisibility(
					visible = showNavRail,
					enter = slideInHorizontally(
						initialOffsetX = { with(density) { -40.dp.roundToPx() } }
					)
				) {
					Column(
						modifier = Modifier
							.fillMaxSize()
							.pointerInput(Unit) {
								detectTapGestures {
									runOnUiThread {
										showNavRail = false
									}
								}
							}
//							.clickable(
//								// Remove ripple effect
//								indication = null,
//								interactionSource = remember { MutableInteractionSource() }
//							) {
//								showNavRail = false
//							}
					) {
						NavigationRail {
							NavigationDrawerItem.items.forEach {
								NavigationRailItem(
									icon = {
										Icon(
											painter = painterResource(id = it.icon ?: R.drawable.ic_dot),
											contentDescription = null,
											modifier = Modifier
												.size(16.dp)
										)
									},
									label = { Text(it.title) },
									selected = currentRoute == it.destination,
									onClick = {
										showNavRail = false
										Handler(Looper.getMainLooper()).postDelayed({
											navController.navigate(it.destination) {
												navController.graph.startDestinationRoute?.let { destination ->
													popUpTo(destination) {
														saveState = true
													}
													
													launchSingleTop = true
													restoreState = true
												}
											}
										}, 300)
									}
								)
							}
						}
					}
				}
			}
		}
	}
}
