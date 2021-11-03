package com.anafthdev.imdbmovie.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.ui.activity.MainActivity
import com.anafthdev.imdbmovie.utils.AppUtils.isConnectedToInternet
import com.anafthdev.imdbmovie.utils.AppUtils.toast
import com.anafthdev.imdbmovie.view_model.MovieViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MostPopularMovieScreen(
	movieViewModel: MovieViewModel
) {
	val context = LocalContext.current
	var hasAlreadyNavigate by remember { mutableStateOf(false) }
	val isRefreshing by movieViewModel.isRefreshing.collectAsState()
	val testData by movieViewModel.mostPopularMovies.observeAsState()
	
	if (!hasAlreadyNavigate) {
		movieViewModel.get(
			MovieViewModel.MOST_POPULAR_MOVIE,
			context.isConnectedToInternet()
		)
		true.also { hasAlreadyNavigate = it }
	}
	
	// TODO: 02/11/2021 get data from api
	
	val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
	SwipeRefresh(
		state = swipeRefreshState,
		onRefresh = {
			movieViewModel.refresh(MovieViewModel.MOST_POPULAR_MOVIE, context.isConnectedToInternet())
		}
	) {
		LazyColumn {
			items(testData!!) {
				MostPopularMovieItem(item = it)
			}
		}
	}
}

@Composable
fun Top250MovieScreen() {

}

@Composable
fun BoxOfficeMovieScreen() {

}

@Composable
fun MovieInformationScreen(movie: Movie) {

}

@Composable
fun SettingsScreen() {

}