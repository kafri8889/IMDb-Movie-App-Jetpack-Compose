package com.anafthdev.imdbmovie.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.ImageResult
import com.anafthdev.imdbmovie.R
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.ui.theme.black
import com.anafthdev.imdbmovie.ui.theme.text_color
import com.anafthdev.imdbmovie.utils.AppUtils.isConnectedToInternet
import com.anafthdev.imdbmovie.view_model.MovieViewModel
import com.anafthdev.notepadcompose.utils.ComposeUtils
import com.anafthdev.notepadcompose.utils.ComposeUtils.Shimmer.applyShimmer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.text.DecimalFormat

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
	val context = LocalContext.current
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
				text = movie.fullTitle,
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(18f, TextUnitType.Sp),
				modifier = Modifier
					.padding(top = 8.dp, bottom = 2.dp, start = 14.dp, end = 8.dp)
					.wrapContentWidth(Alignment.Start)
			)
			
			Text(
				text = run {
					val genres = movie.genres.split(", ".toRegex())
					"${if (genres.size > 1) "${genres[0]}  |  ${genres[1]}" else genres[0]}  |  ${movie.runtimeStr}  |  ${movie.releaseDate}"
				},
				fontWeight = FontWeight.Light,
				fontSize = TextUnit(13f, TextUnitType.Sp),
				modifier = Modifier
					.padding(start = 14.dp, end = 8.dp, top = 2.dp, bottom = 8.dp)
			)
			
			Row(
				modifier = Modifier.padding(start = 14.dp, end = 8.dp, bottom = 8.dp)
			) {
				
				// Rating
				Column(
					modifier = Modifier.weight(2f),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Image(
						painter = painterResource(id = R.drawable.ic_star_24),
						contentDescription = null,
						modifier = Modifier.size(32.dp)
					)
					
					Text(
						text = buildAnnotatedString {
							withStyle(
								SpanStyle(
									color = black,
									fontWeight = FontWeight.SemiBold,
									fontSize = TextUnit(12f, TextUnitType.Sp),
								)
							) {
								append(movie.imDbRating)
							}
							
							withStyle(
								SpanStyle(
									color = black,
									fontWeight = FontWeight.Light,
									fontSize = TextUnit(12f, TextUnitType.Sp),
								)
							) {
								append(" / 10")
							}
						},
						modifier = Modifier.padding(top = 4.dp)
					)
					
					Text(
						text = run {
							val format = DecimalFormat("###,###.##")
							val vote = format.format(movie.imDbRatingVotes.toLong()).replace(',', '.')
							
							"$vote reviews"
						},
						color = text_color,
						fontWeight = FontWeight.Normal,
						fontSize = TextUnit(12f, TextUnitType.Sp),
					)
				}
				
				if (movie.metacriticRating.isNotBlank()) {
					Divider(
						color = text_color,
						thickness = 1.dp,
						modifier = Modifier
							.height(64.dp)
							.weight(0.01f)
							.align(Alignment.CenterVertically)
					)
					
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier
							.weight(2f)
					) {
						Image(
							painter = painterResource(id = R.drawable.ic_metascore),
							contentDescription = null,
							modifier = Modifier.size(32.dp)
						)
						
						Text(
							text = "META SCORE",
							color = black,
							fontWeight = FontWeight.SemiBold,
							fontSize = TextUnit(12f, TextUnitType.Sp),
							modifier = Modifier.padding(top = 4.dp)
						)
						
						Text(
							text = movie.metacriticRating,
							color = text_color,
							fontWeight = FontWeight.Normal,
							fontSize = TextUnit(12f, TextUnitType.Sp),
						)
					}
				}
				
			}
			
			Text(
				text = "Genres",
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(16f, TextUnitType.Sp),
				modifier = Modifier
					.padding(start = 14.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
			)
			
			Text(
				text = movie.genres,
				fontWeight = FontWeight.Normal,
				fontSize = TextUnit(14f, TextUnitType.Sp),
				modifier = Modifier
					.padding(start = 14.dp, end = 8.dp, top = 2.dp, bottom = 8.dp)
			)
			
			Text(
				text = "Awards",
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(16f, TextUnitType.Sp),
				modifier = Modifier
					.padding(start = 14.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
			)
			
			Text(
				text = movie.awards,
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