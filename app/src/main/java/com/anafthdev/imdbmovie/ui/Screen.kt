package com.anafthdev.imdbmovie.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.ImageResult
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.ui.activity.MainActivity
import com.anafthdev.imdbmovie.utils.AppUtils.isConnectedToInternet
import com.anafthdev.imdbmovie.utils.AppUtils.toast
import com.anafthdev.imdbmovie.view_model.MovieViewModel
import com.anafthdev.notepadcompose.utils.ComposeUtils
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.anafthdev.notepadcompose.utils.ComposeUtils.Shimmer.applyShimmer

@Composable
fun MostPopularMovieScreen(
	navigationController: NavHostController,
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
				MostPopularMovieItem(
					item = it,
					navHostController = navigationController
				)
			}
		}
	}
}

@Composable
fun Top250MovieScreen(
	navigationController: NavHostController
) {

}

@Composable
fun BoxOfficeMovieScreen(
	navigationController: NavHostController
) {

}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun MovieInformationScreen(
	navigationController: NavHostController,
	movieID: String
) {
	var shimmerState by remember { mutableStateOf(ComposeUtils.Shimmer.START) }
	
	// TODO: 05/11/2021 get Movie with movieID
	
	val movie = Movie.item1
	
	Column {
		Image(
			painter = rememberImagePainter(
				data = movie.image,
				builder = {
					diskCachePolicy(CachePolicy.ENABLED)
					listener(object : ImageRequest.Listener {
						override fun onError(request: ImageRequest, throwable: Throwable) {
							super.onError(request, throwable)
							throwable.printStackTrace()
							Log.e("ImageRequest", "error: ${throwable.message}")
						}
						
						override fun onStart(request: ImageRequest) {
							super.onStart(request)
							shimmerState = ComposeUtils.Shimmer.START
						}
						
						override fun onSuccess(request: ImageRequest, metadata: ImageResult.Metadata) {
							super.onSuccess(request, metadata)
							shimmerState = ComposeUtils.Shimmer.STOP
						}
					})
				}
			),
			contentDescription = null,
			modifier = Modifier
				.padding(top = 8.dp)
				.fillMaxWidth()
				.weight(2f, true)
				.applyShimmer(shimmerState)
		)
		
		Column {
			Text(
				text = movie.title,
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(18f, TextUnitType.Sp),
				modifier = Modifier
					.padding(start = 14.dp, end = 8.dp, top = 4.dp, bottom = 2.dp)
			)
			
			Text(
				text = movie.plot,
				fontWeight = FontWeight.Normal,
				fontSize = TextUnit(14f, TextUnitType.Sp),
				modifier = Modifier
					.padding(start = 14.dp, end = 8.dp, top = 2.dp, bottom = 8.dp)
			)
		}
	}
}

@Preview(showSystemUi = true)
@Composable
fun MovieInformationScreenPreview() {
	val navController = rememberNavController()
	MovieInformationScreen(
		movieID = "",
		navigationController = navController
	)
}

@Composable
fun SettingsScreen() {

}