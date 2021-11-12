package com.anafthdev.imdbmovie.ui

import android.app.Activity
import android.app.Application
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.util.Log.i
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.request.ImageResult
import com.anafthdev.imdbmovie.R
import com.anafthdev.imdbmovie.data.*
import com.anafthdev.imdbmovie.model.SettingsPreferences
import com.anafthdev.imdbmovie.model.most_popular_movie.MostPopularMovie
import com.anafthdev.imdbmovie.model.movie.*
import com.anafthdev.imdbmovie.ui.theme.black
import com.anafthdev.imdbmovie.ui.theme.default_primary
import com.anafthdev.imdbmovie.ui.theme.default_secondary
import com.anafthdev.imdbmovie.ui.theme.text_color
import com.anafthdev.imdbmovie.utils.AppDatastore
import com.anafthdev.imdbmovie.utils.AppUtils.get
import com.anafthdev.imdbmovie.utils.AppUtils.toast
import com.anafthdev.imdbmovie.utils.DatabaseUtils
import com.anafthdev.imdbmovie.utils.NetworkUtil
import com.anafthdev.imdbmovie.view_model.MovieViewModel
import com.anafthdev.notepadcompose.utils.ComposeUtils
import com.anafthdev.notepadcompose.utils.ComposeUtils.Shimmer.applyShimmer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalUnitApi::class, ExperimentalMaterialApi::class)
@Composable
fun DrawerItem(item: NavigationDrawerItem, isSelected: Boolean = false, onClick: () -> Unit) {
	val backgroundColor = if (isSelected) default_primary.copy(alpha = 0.1f) else Color.Transparent
	
	Card(
		onClick = onClick,
		backgroundColor = backgroundColor,
		shape = RoundedCornerShape(8.dp),
		elevation = 0.dp
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				
				.fillMaxWidth()
				.padding(8.dp)
		) {
			item.icon?.let {
				Image(
					painter = painterResource(id = item.icon),
					contentDescription = null,
					modifier = Modifier
						.size(10.dp)
				)
			}
			
			Text(
				text = item.title,
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(16f, TextUnitType.Sp),
				modifier = Modifier
					.padding(start = if (item.icon == null) 0.dp else 8.dp)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DrawerItemPreview() {
	DrawerItem(item = NavigationDrawerItem.MostPopularMovies, true) {}
}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navHostController: NavController) {
	val navigationBackStackEntry by navHostController.currentBackStackEntryAsState()
	val currentRoute = navigationBackStackEntry?.destination?.route
	
	val isDividerSet = remember { mutableStateOf(false) }
	
	Column(modifier = Modifier
		.padding(4.dp)
		.fillMaxHeight()
	) {
		LazyColumn {
			items(NavigationDrawerItem.items) { item ->
				if ((item.icon == null) and !isDividerSet.value) {
					Divider()
					isDividerSet.value = false
				}
				DrawerItem(item = item, isSelected = currentRoute == item.destination) {
					navHostController.navigate(item.destination) {
						navHostController.graph.startDestinationRoute?.let { destination ->
							popUpTo(destination) {
								saveState = true
							}
							
							launchSingleTop = true
							restoreState = true
						}
					}
					
					scope.launch { scaffoldState.drawerState.close() }
				}
			}
		}
	}
}

@Preview(showSystemUi = true)
@Composable
fun DrawerPreview() {
	val scope = rememberCoroutineScope()
	val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
	val navController = rememberNavController()
	Drawer(scope, scaffoldState, navController)
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalUnitApi::class)
@Composable
fun MostPopularMovieItem(
	item: MostPopularMovie,
	navHostController: NavHostController,
	isNetworkAvailable: Boolean
) {
//	val isConnectedToInternet by remember { mutableStateOf(isNetworkAvailable) }
	
	Card(
		shape = RoundedCornerShape(14.dp),
		elevation = 3.dp,
		modifier = Modifier
			.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
		onClick = {
			val destination = NavigationDestination.MOVIE_INFORMATION_SCREEN
			navHostController.navigate(
				"$destination/${item.id}"
			) {
				navHostController.graph.startDestinationRoute?.let { destination ->
					popUpTo(destination) {
						saveState = true
					}
					
					launchSingleTop = true
					restoreState = true
				}
			}
		}
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
		) {
			var shimmerState by remember { mutableStateOf(ComposeUtils.Shimmer.START) }
			Image(
				contentDescription = null,
				contentScale = ContentScale.Crop,
				painter = rememberImagePainter(
					data = item.image,
					builder = {
						if (isNetworkAvailable) data(item.image)
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
				modifier = Modifier
					.height(512.dp)
					.fillMaxWidth()
					.clip(
						RoundedCornerShape(
							topEnd = 14.dp,
							topStart = 14.dp
						)
					)
					.applyShimmer(shimmerState)
			)
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
			) {
				Text(
					text = item.fullTitle,
					fontWeight = FontWeight.Bold,
					fontSize = TextUnit(18f, TextUnitType.Sp),
					modifier = Modifier
						.padding(end = 8.dp)
						.weight(1f)
						.wrapContentWidth(Alignment.Start)
				)
				
				Row(
					modifier = Modifier
						.weight(1f)
						.wrapContentWidth(Alignment.End),
					verticalAlignment = Alignment.CenterVertically
				) {
					Image(
						painter = painterResource(id = R.drawable.ic_star_24),
						contentDescription = null,
						modifier = Modifier.size(28.dp)
					)
					Column {
						Text(
							text = buildAnnotatedString {
								withStyle(
									SpanStyle(
										color = text_color,
										fontWeight = FontWeight.ExtraBold,
										fontSize = TextUnit(14f, TextUnitType.Sp),
									)
								) {
									append(item.imDbRating)
								}
								
								withStyle(
									SpanStyle(
										color = text_color,
										fontWeight = FontWeight.Light,
										fontSize = TextUnit(12f, TextUnitType.Sp),
									)
								) {
									append("/10")
								}
							},
							modifier = Modifier.align(Alignment.CenterHorizontally)
						)
						
						Text(
							text = item.imDbRatingCount,
							color = text_color,
							fontWeight = FontWeight.Normal,
							fontSize = TextUnit(12f, TextUnitType.Sp),
						)
					}
				}
			}
		}
	}
}

@Preview(showSystemUi = true)
@Composable
fun MostPopularMovieItemPreview() {
	val navHostController = rememberNavController()
	MostPopularMovieItem(
		item = MostPopularMovie.item1,
		navHostController = navHostController,
		isNetworkAvailable = false
	)
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ActorItem(actor: Actor) {
	var shimmerState by remember { mutableStateOf(ComposeUtils.Shimmer.START) }
	
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp)
	) {
		Image(
			painter = rememberImagePainter(
				data = actor.image,
				builder = {
					listener(object : ImageRequest.Listener {
						override fun onError(request: ImageRequest, throwable: Throwable) {
							super.onError(request, throwable)
							i("ImageRequest", throwable.message!!)
							throwable.message
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
				.size(72.dp)
				.weight(1f, false)
				.clip(RoundedCornerShape(100))
				.applyShimmer(shimmerState)
		)
		
		Column(
			verticalArrangement = Arrangement.Center,
			modifier = Modifier
				.weight(4f)
				.padding(start = 8.dp)
		) {
			Text(
				text = actor.name,
				color = black,
				fontSize = TextUnit(14f, TextUnitType.Sp),
				fontWeight = FontWeight.SemiBold,
				modifier = Modifier
					.fillMaxWidth()
			)
			
			Text(
				text = actor.asCharacter,
				color = text_color,
				fontSize = TextUnit(14f, TextUnitType.Sp),
				fontWeight = FontWeight.Normal,
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 4.dp)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun ActorItemPreview() {
	val actor = Movie.item1.actorList[0]
	ActorItem(actor = actor)
}

@OptIn(
	ExperimentalUnitApi::class,
	ExperimentalMaterialApi::class,
)
@Composable
fun SimilarItem(
	navHostController: NavHostController?,
	viewModel: MovieViewModel,
	similar: Similar?
) {
	val shimmerList = ComposeUtils.Shimmer.createShimmer(
		"image",
		"full_title"
	)
	Card(
		shape = RoundedCornerShape(12.dp),
		elevation = 4.dp,
		onClick = {
			if (similar != null) {
				viewModel.getMovie(similar.id, true)
			}
		},
		modifier = Modifier
			.padding(8.dp)
	) {
		Column {
			Image(
				painter = rememberImagePainter(
					data = similar?.image ?: "",
					builder = {
						listener(object : ImageRequest.Listener {
							override fun onError(request: ImageRequest, throwable: Throwable) {
								super.onError(request, throwable)
								Timber.i(throwable.message!!)
								throwable.printStackTrace()
							}
							
							override fun onStart(request: ImageRequest) {
								super.onStart(request)
								shimmerList.get { it.tag == "image" }!!.state = ComposeUtils.Shimmer.START
							}
							
							override fun onSuccess(request: ImageRequest, metadata: ImageResult.Metadata) {
								super.onSuccess(request, metadata)
								shimmerList.get { it.tag == "image" }!!.state = ComposeUtils.Shimmer.STOP
							}
						})
					}
				),
				contentDescription = null,
				contentScale = ContentScale.FillBounds,
				modifier = Modifier
					.width(172.dp)
					.height(256.dp)
					.applyShimmer(shimmerList.get { it.tag == "image" }!!.state)
			)
			
			if (similar != null) {
				Text(
					text = similar.fullTitle,
					color = black,
					maxLines = 1,
					fontSize = TextUnit(14f, TextUnitType.Sp),
					fontWeight = FontWeight.SemiBold,
					modifier = Modifier
						.width(172.dp)
						.padding(4.dp)
				)
			} else {
				Text(
					text = "default title",
					color = Color.Transparent,
					fontSize = TextUnit(14f, TextUnitType.Sp),
					fontWeight = FontWeight.SemiBold,
					modifier = Modifier
						.height(20.dp)
						.padding(4.dp)
						.clip(RoundedCornerShape(8.dp))
						.applyShimmer(shimmerList.get { it.tag == "full_title" }!!.state)
				)
			}
		}
	}
}

@Preview(showBackground = false)
@Composable
fun SimilarItemPreview() {
	val context = LocalContext.current
	val navHostController = rememberNavController()
	SimilarItem(
		navHostController = navHostController,
		viewModel = MovieViewModel(
			Application(),
			Repository(
				LocalDataSource(DatabaseUtils(context)),
				RemoteDataSource()
			),
			AppDatastore(context),
			NetworkUtil(context)
		),
		similar = Movie.item1.similars[0]
	)
}

@Composable
fun PosterItem(poster: Poster) {
	var shimmerState by remember { mutableStateOf(ComposeUtils.Shimmer.START) }
	
	Image(
		painter = rememberImagePainter(
			data = poster.link,
			builder = {
				listener(object : ImageRequest.Listener {
					override fun onError(request: ImageRequest, throwable: Throwable) {
						super.onError(request, throwable)
						i("ImageRequest", throwable.message!!)
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
			.padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
			.size(128.dp, 224.dp)
			.applyShimmer(shimmerState)
	)
}

@Preview(showBackground = false)
@Composable
fun PosterItemPreview() {
	PosterItem(poster = Movie.item1.posters!!.posters[0])
}

@Composable
fun BackdropItem(backdrop: Backdrop) {
	var shimmerState by remember { mutableStateOf(ComposeUtils.Shimmer.START) }
	
	Image(
		painter = rememberImagePainter(
			data = backdrop.link,
			builder = {
				listener(object : ImageRequest.Listener {
					override fun onError(request: ImageRequest, throwable: Throwable) {
						super.onError(request, throwable)
						i("ImageRequest", throwable.message!!)
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
			.padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
			.size(128.dp, 224.dp)
			.applyShimmer(shimmerState)
	)
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun SettingsPreferences(
	preferences: SettingsPreferences,
	onClick: (Any?) -> Unit
) {
	when (preferences.type) {
		SettingsPreferences.PreferencesType.BASIC -> {
			Column(
				verticalArrangement = Arrangement.Center,
				modifier = Modifier
					.fillMaxWidth()
					.height(64.dp)
					.clickable { onClick(null) }
			) {
				Text(
					text = preferences.title,
					color = black,
					fontSize = TextUnit(16f, TextUnitType.Sp),
					fontWeight = FontWeight.SemiBold,
					modifier = Modifier.padding(start = 12.dp)
				)
				
				if (preferences.summary != null) {
					Text(
						text = preferences.summary,
						color = text_color,
						fontSize = TextUnit(14f, TextUnitType.Sp),
						fontWeight = FontWeight.Normal,
						modifier = Modifier.padding(start = 12.dp)
					)
				}
			}
		}
		
		SettingsPreferences.PreferencesType.SWITCH -> {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.fillMaxWidth()
					.height(64.dp)
					.clickable {
						onClick(!preferences.checked)
					}
			) {
				Column(
					verticalArrangement = Arrangement.Center,
					modifier = Modifier
						.weight(2f)
				) {
					Text(
						text = preferences.title,
						color = black,
						fontSize = TextUnit(16f, TextUnitType.Sp),
						fontWeight = FontWeight.SemiBold,
						modifier = Modifier.padding(start = 12.dp)
					)
					
					if (preferences.summary != null) {
						Text(
							text = preferences.summary,
							color = text_color,
							fontSize = TextUnit(14f, TextUnitType.Sp),
							fontWeight = FontWeight.Normal,
							modifier = Modifier.padding(start = 12.dp)
						)
					}
				}
				
				Switch(
					checked = preferences.checked,
					colors = SwitchDefaults.colors(
						checkedThumbColor = default_primary,
						checkedTrackColor = default_primary,
						checkedTrackAlpha = 0.4f
					),
					onCheckedChange = {
						onClick(!preferences.checked)
					},
					modifier = Modifier
						.weight(0.4f)
				)
			}
		}
	}
}

@Preview(name = "SettingsPreferencesPreview_Basic", showBackground = true)
@Composable
fun SettingsPreferencesPreviewBasic() {
	val preferences = SettingsPreferences(
		"Title",
		"Summary",
		type = SettingsPreferences.PreferencesType.BASIC
	)
	SettingsPreferences(preferences = preferences) {}
}

@Preview(name = "SettingsPreferencesPreview_Switch", showBackground = true)
@Composable
fun SettingsPreferencesPreviewSwitch() {
	val preferences = SettingsPreferences(
		"Title",
		"Summary",
		type = SettingsPreferences.PreferencesType.SWITCH
	)
	SettingsPreferences(preferences = preferences) {}
}
