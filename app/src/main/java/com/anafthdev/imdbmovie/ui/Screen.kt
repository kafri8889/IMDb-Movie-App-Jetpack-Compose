package com.anafthdev.imdbmovie.ui

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Log.i
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.ImageResult
import com.anafthdev.imdbmovie.R
import com.anafthdev.imdbmovie.api.APIService
import com.anafthdev.imdbmovie.model.SettingsPreferences
import com.anafthdev.imdbmovie.model.movie.Movie
import com.anafthdev.imdbmovie.ui.theme.black
import com.anafthdev.imdbmovie.ui.theme.text_color
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.AppUtils.isConnectedToInternet
import com.anafthdev.imdbmovie.utils.AppUtils.toast
import com.anafthdev.imdbmovie.view_model.MovieViewModel
import com.anafthdev.notepadcompose.utils.ComposeUtils
import com.anafthdev.notepadcompose.utils.ComposeUtils.Shimmer.applyShimmer
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.IndexOutOfBoundsException
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

@OptIn(
	ExperimentalUnitApi::class,
	ExperimentalPagerApi::class,
	ExperimentalFoundationApi::class
)
@Composable
fun MovieInformationScreen(
	navigationController: NavHostController,
	viewModel: MovieViewModel,
	movieID: String
) {
	val context = LocalContext.current
	val scope = rememberCoroutineScope()
	val pagerState = rememberPagerState()
	val currentMovieID by viewModel.currentMovieID.collectAsState()
	val currentMovie by viewModel.currentMovie.observeAsState()
	
	var shimmerState by remember { mutableStateOf(ComposeUtils.Shimmer.START) }
	var isRequestExecute by remember { mutableStateOf(false) }
	
//	viewModel.setMovieID(movieID)
//	viewModel.getMovie(currentMovieID)
	
	// prevent for multiple api call
	if (!isRequestExecute) {
		viewModel.getMovie(movieID)
		isRequestExecute = true
	}
	
//	val movie = Movie.item1
	val movie = if (currentMovie == null) Movie.item1 else currentMovie!!
	val pages = listOf("Overview", "Actors", "Posters")
	
	Column {
		Column(
			modifier = Modifier
				.wrapContentHeight()
				.verticalScroll(rememberScrollState())
				.weight(1f, false)
		) {
			ConstraintLayout {
				val (image, contentRating) = createRefs()
				
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
					contentScale = ContentScale.Crop,
					contentDescription = null,
					modifier = Modifier
						.height(512.dp)
						.fillMaxWidth()
						.applyShimmer(shimmerState)
						.constrainAs(image) {
							top.linkTo(parent.top)
							start.linkTo(parent.start)
							end.linkTo(parent.end)
						}
				)
				
				Column(
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
						.size(72.dp)
						.padding(16.dp)
						.clip(RoundedCornerShape(12.dp))
						.constrainAs(contentRating) {
							top.linkTo(parent.top)
							end.linkTo(parent.end)
						}
						.background(Color(0xFFF0F0F0))
				) {
					Text(
						text = "${movie.contentRating ?: "0"}+",
						fontWeight = FontWeight.SemiBold
					)
				}
			}
			
			Column {
				Text(
					text = movie.fullTitle ?: "-",
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(18f, TextUnitType.Sp),
					modifier = Modifier
						.padding(top = 8.dp, bottom = 2.dp, start = 14.dp, end = 8.dp)
						.wrapContentWidth(Alignment.Start)
				)
				
				Text(
					text = run {
						if (movie.genres == null) "-  |  -  |  -"
						else {
							val genres = movie.genres.split(", ".toRegex())
							"${genres[0]}  |  ${movie.runtimeStr}  |  ${movie.releaseDate}"
						}
					},
					fontWeight = FontWeight.Light,
					fontSize = TextUnit(13f, TextUnitType.Sp),
					modifier = Modifier
						.padding(start = 14.dp, end = 8.dp, top = 2.dp, bottom = 8.dp)
						.fillMaxWidth()
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
									append(movie.imDbRating ?: "0")
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
								val vote = if (movie.imDbRatingVotes == null) "0" else {
									format.format(movie.imDbRatingVotes.toLong()).replace(',', '.')
								}
								
								"$vote votes"
							},
							color = text_color,
							fontWeight = FontWeight.Normal,
							fontSize = TextUnit(12f, TextUnitType.Sp),
						)
					}
					
					// Meta score
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
							text = movie.metacriticRating ?: "0",
							color = text_color,
							fontWeight = FontWeight.Normal,
							fontSize = TextUnit(12f, TextUnitType.Sp),
						)
					}
					
					// Awards
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
							painter = painterResource(id = R.drawable.ic_award_symbol),
							contentDescription = null,
							modifier = Modifier.size(32.dp)
						)
						
						Text(
							text = "AWARDS",
							color = black,
							fontWeight = FontWeight.SemiBold,
							fontSize = TextUnit(12f, TextUnitType.Sp),
							modifier = Modifier.padding(top = 4.dp)
						)
						
						Text(
							text = run {
								if (movie.awards != null) {
									val awards = movie.awards.split("\\|".toRegex())
									
									awards.size.toString()
								} else "0"
							},
							color = text_color,
							fontWeight = FontWeight.Normal,
							fontSize = TextUnit(12f, TextUnitType.Sp),
						)
					}
					
				}
				
				TabRow(
					selectedTabIndex = pagerState.currentPage,
					indicator = { tabPositions ->
						TabRowDefaults.Indicator(
							Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
						)
					},
					modifier = Modifier
						.fillMaxWidth()
						.wrapContentHeight()
				) {
					pages.forEachIndexed { index, s ->
						Tab(
							text = { Text(s) },
							selected = pagerState.currentPage == index,
							onClick = {
								scope.launch {
									pagerState.animateScrollToPage(index)
								}
							})
					}
				}
				
				HorizontalPager(
					count = pages.size,
					state = pagerState,
					modifier = Modifier
						.fillMaxWidth()
						.height(256.dp)
				) { page ->
					when (page) {
						0 -> OverviewScreen(movie = movie)
						1 -> ActorScreen(movie = movie)
						2 -> PosterScreen(movie = movie)
					}
					
					i("pager", "p: ${this.currentPage}")
				}
				
				Text(
					text = "Directors",
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(16f, TextUnitType.Sp),
					modifier = Modifier
						.padding(start = 14.dp, end = 8.dp, top = 18.dp, bottom = 2.dp)
				)
				
				val directors = if (movie.directors == null) listOf("-") else {
					movie.directors.split(", ".toRegex())
				}
				
				FlowRow(
					modifier = Modifier
						.fillMaxWidth()
						.wrapContentHeight()
						.padding(start = 10.dp)
				) {
					for (director in directors) {
						Text(
							text = director,
							color = black,
							fontWeight = FontWeight.Normal,
							fontSize = TextUnit(14f, TextUnitType.Sp),
							modifier = Modifier
								.padding(4.dp)
								.clip(RoundedCornerShape(100))
								.background(Color(0xFFECECEC))
								.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
						)
					}
				}
				
				Text(
					text = "Genres",
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(16f, TextUnitType.Sp),
					modifier = Modifier
						.padding(start = 14.dp, end = 8.dp, top = 18.dp, bottom = 2.dp)
				)
				
				val genres = if (movie.genres == null) listOf("-") else {
					movie.genres.split(", ".toRegex())
				}
				FlowRow(
					modifier = Modifier
						.fillMaxWidth()
						.wrapContentHeight()
						.padding(start = 10.dp)
				) {
					for (genre in genres) {
						Text(
							text = genre,
							color = black,
							fontWeight = FontWeight.Normal,
							fontSize = TextUnit(14f, TextUnitType.Sp),
							modifier = Modifier
								.padding(4.dp)
								.clip(RoundedCornerShape(100))
								.background(Color(0xFFECECEC))
								.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
						)
					}
				}		
				
				val awards = if (movie.awards == null) listOf("-") else {
					movie.awards.split("\\|".toRegex())
				}
				
				Text(
					text = "Awards",
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(16f, TextUnitType.Sp),
					modifier = Modifier
						.padding(start = 14.dp, end = 8.dp, top = 16.dp, bottom = 2.dp)
				)
				
				FlowRow(
					modifier = Modifier
						.fillMaxWidth()
						.wrapContentHeight()
						.padding(start = 10.dp)
				) {
					for (award in awards) {
						Text(
							text = award,
							textAlign = TextAlign.Center,
							color = black,
							fontWeight = FontWeight.Normal,
							fontSize = TextUnit(14f, TextUnitType.Sp),
							modifier = Modifier
								.padding(4.dp)
								.clip(RoundedCornerShape(100))
								.background(Color(0xFFECECEC))
								.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
						)
					}
				}
				
				Text(
					text = "Similar",
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(16f, TextUnitType.Sp),
					modifier = Modifier
						.padding(start = 14.dp, end = 8.dp, top = 16.dp, bottom = 2.dp)
				)
				
				CompositionLocalProvider(
					LocalOverScrollConfiguration provides null
				) {
					LazyRow {
						items(movie.similars) {
							SimilarItem(
								navHostController = navigationController,
								similar = it
							)
						}
					}
				}
			}
		}
	}
}

@Preview(showSystemUi = true)
@Composable
fun MovieInformationScreenPreview() {
	val navController = rememberNavController()
	MovieInformationScreen(
		movieID = "",
		viewModel = viewModel(),
		navigationController = navController
	)
}

@Composable
fun SettingsScreen(
	appDatastore: AppDatastore,
	scope: CoroutineScope
) {
	val context = LocalContext.current
	var apiKeySummary by remember {
		mutableStateOf("Set your API key here!")
	}
	var isUseSampleData by remember { mutableStateOf(false) }
	var isSetApiKeyDialogShowed by remember { mutableStateOf(false) }
	
	val settingsPreferences = listOf(
		SettingsPreferences(
			"API Key",
			run {
				scope.launch {
					appDatastore.getApiKey.collect {
						if (it != AppDatastore.ERROR_NO_API_KEY) {
							apiKeySummary = it
						}
					}
				}
				
				apiKeySummary
			}
		),
		SettingsPreferences(
			"Use Sample Data",
			checked = run {
				scope.launch {
					appDatastore.isUseSampleData.collect {
						Handler(Looper.getMainLooper()).post {
							isUseSampleData = it
						}
					}
				}
				
				isUseSampleData
			},
			type = SettingsPreferences.PreferencesType.SWITCH
		)
	)
	
	if (isSetApiKeyDialogShowed) SetAPIKeyDialog(
		onSave = { apiKey ->
			scope.launch {
				appDatastore.setApiKey(apiKey)
			}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
				isSetApiKeyDialogShowed = false
				apiKeySummary = apiKey
			} }
		},
		onDismiss = { isSetApiKeyDialogShowed = false }
	)
	
	LazyColumn {
		items(settingsPreferences) { preferences ->
			SettingsPreferences(preferences = preferences) {
				when (preferences) {
					settingsPreferences[0] -> isSetApiKeyDialogShowed = true
					settingsPreferences[1] -> {
						// TODO: 10/11/2021 update is use sample data
						val mIsUseSampleData = it.toString() == "true"
						scope.launch {
							appDatastore.setUseSampleData(mIsUseSampleData)
						}.invokeOnCompletion { Handler(Looper.getMainLooper()).post {
							isUseSampleData = mIsUseSampleData
						} }
					}
				}
			}
			
			Divider()
		}
	}
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
	val settingsPreferences = listOf(
		SettingsPreferences(
			"API Key",
			"Set your API key here!"
		),
		SettingsPreferences(
			"Use Sample Data",
			type = SettingsPreferences.PreferencesType.SWITCH
		)
	)
	
	LazyColumn {
		items(settingsPreferences) {
			SettingsPreferences(preferences = it) {}
			Divider()
		}
	}
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun OverviewScreen(movie: Movie) {
	var shimmerState by remember { mutableStateOf(ComposeUtils.Shimmer.START) }
	
	Row(
		modifier = Modifier
			.fillMaxSize()
	) {
		if (movie.posters == null) {
			Image(
				painter = ColorPainter(Color.Gray),
				contentDescription = null,
				modifier = Modifier
					.weight(1f)
					.padding(4.dp)
			)
		} else {
			Image(
				painter = rememberImagePainter(
					data = "${APIService.POSTER_URL}${(movie.posters!!.posters[0].id)}",
					builder = {
						listener(object : ImageRequest.Listener {
							override fun onError(request: ImageRequest, throwable: Throwable) {
								super.onError(request, throwable)
								Timber.i("error: ${throwable.message}")
								throwable.printStackTrace()
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
					.weight(1f)
					.padding(4.dp)
					.applyShimmer(shimmerState)
			)
		}
		
		Column(
			modifier = Modifier
				.weight(2f)
				.padding(8.dp)
				.verticalScroll(rememberScrollState())
		) {
			Text(
				text = movie.plot ?: "-",
				color = text_color,
				fontSize = TextUnit(14f, TextUnitType.Sp),
				letterSpacing = TextUnit(0.9f, TextUnitType.Sp),
				lineHeight = TextUnit(20f, TextUnitType.Sp)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun OverviewScreenPreview() {
	OverviewScreen(movie = Movie.item1)
}

@Composable
fun ActorScreen(movie: Movie) {
	if (movie.actorList.isNotEmpty()) {
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
		) {
			items(movie.actorList) { actor ->
				ActorItem(actor = actor)
			}
		}
	} else {
		Column(
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.fillMaxSize()
		) {
			Text(text = "No Actors")
		}
	}
}

@Preview(showBackground = true)
@Composable
fun ActorScreenPreview() {
	ActorScreen(movie = Movie.item1)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PosterScreen(movie: Movie) {
	if (movie.posters != null) {
		LazyVerticalGrid(
			cells = GridCells.Fixed(2)
		) {
			items(movie.posters.posters) {
				PosterItem(poster = it)
			}
			
			items(movie.posters.backdrops) {
				BackdropItem(backdrop = it)
			}
		}
	} else {
		Column(
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.fillMaxSize()
		) {
			Text(text = "No Posters")
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PosterScreenPreview() {
	PosterScreen(Movie.item1)
}

@OptIn(ExperimentalUnitApi::class)
@Preview(showBackground = true)
@Composable
fun ItemPrev() {

}