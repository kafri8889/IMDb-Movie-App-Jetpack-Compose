package com.anafthdev.imdbmovie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anafthdev.imdbmovie.R
import com.anafthdev.imdbmovie.data.NavigationDrawerItem
import com.anafthdev.imdbmovie.model.movie.*
import com.anafthdev.imdbmovie.ui.theme.default_dark_primary
import com.anafthdev.imdbmovie.ui.theme.default_primary
import com.anafthdev.imdbmovie.utils.AppUtils.lessThan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun MovieView(movie: Movie) {

}

@Preview(name = "MovieView", showBackground = true)
@Composable
fun MovieViewPreview() {
	val movie = Movie(
		"Top Rated Movies #31 | 6 wins & 15 nominations.",
		"Gaumont, Les Films du Dauphin, Columbia Pictures",
		"R",
		"France, USA",
		"Luc Besson",
		"",
		"Léon: The Professional (1994)",
		"Action, Crime, Drama, Thriller",
		"tt0110413",
		"8.5",
		"1050598",
		"https://imdb-api.com/images/original/MV5BODllNWE0MmEtYjUwZi00ZjY3LThmNmQtZjZlMjI2YTZjYmQ0XkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_Ratio0.6791_AL_.jpg",
		"girl,hitman,teenage girl,older man young girl relationship,neo noir",
		"English, Italian, French",
		"64",
		"Léon",
		"After her father, step-mother, step-sister and little brother are killed by her father's employers, the 12-year-old daughter of an abject drug dealer manages to take refuge in the apartment of a professional hitman who at her request teaches her the methods of his job so she can take her revenge on the corrupt DEA agent who ruined her life by killing her beloved brother.",
		"",
		false,
		"1994-09-14",
		"110",
		"1h 50mins",
		"Jean Reno, Gary Oldman, Natalie Portman, Danny Aiello",
		"A perfect assassin. An innocent girl. They have nothing left to lose except each other. He moves without sound. Kills without emotion. Disappears without trace. Only a 12 year old girl... knows his weakness.",
		"Léon: The Professional",
		"Movie",
		"Luc Besson",
		"1994",
		BoxOffice(
			"FRF115,000,000 (estimated)",
			"$5,306,558, 20 November 1994",
			"$19,501,238",
			"$19,552,639"
		),
		listOf(
			Writer(
				"nm0000108",
				"Luc Besson"
			)
		),
		listOf(
			Similar(
				"David Fincher",
				"Se7en (1995)",
				"Crime, Drama, Mystery",
				"tt0114369",
				"8.6",
				"https://imdb-api.com/images/original/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_Ratio0.6737_AL_.jpg",
				"Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.",
				"Morgan Freeman, Brad Pitt, Kevin Spacey",
				"Se7en",
				"1995",
			),
			Similar(
				"Frank Darabont",
				"The Green Mile (1999)",
				"Crime, Drama, Fantasy",
				"tt0120689",
				"8.6",
				"https://imdb-api.com/images/original/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1_Ratio0.6737_AL_.jpg",
				"The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.",
				"Tom Hanks, Michael Clarke Duncan, David Morse",
				"The Green Mile",
				"1999",
			),
			Similar(
				"Tony Kaye",
				"American History X (1998)",
				"Drama",
				"tt0120586",
				"8.5",
				"https://imdb-api.com/images/original/MV5BZTJhN2FkYWEtMGI0My00YWM4LWI2MjAtM2UwNjY4MTI2ZTQyXkEyXkFqcGdeQXVyNjc3MjQzNTI@._V1_Ratio0.6737_AL_.jpg",
				"A former neo-nazi skinhead tries to prevent his younger brother from going down the same wrong path that he did.",
				"Edward Norton, Edward Furlong, Beverly D'Angelo",
				"American History X",
				"1998",
			),
			Similar(
				"David Fincher",
				"Fight Club (1999)",
				"Drama",
				"tt0137523",
				"8.8",
				"https://imdb-api.com/images/original/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_Ratio0.6737_AL_.jpg",
				"A nameless first person narrator (Edward Norton) attends support groups in attempt to subdue his emotional state and relieve his insomniac state. When he meets Marla (Helena Bonham Carter).",
				"Brad Pitt, Edward Norton, Meat Loaf",
				"Fight Club",
				"1999",
			)
		),
		listOf(
			Star(
				"nm0000606",
				"Jean Reno"
			),
			Star(
				"nm0000198",
				"Gary Oldman"
			),
			Star(
				"nm0000204",
				"Natalie Portman"
			),
			Star(
				"nm0000732",
				"Danny Aiello"
			)
		),
		listOf(
			Actor(
				"Leon",
				"nm0000606",
				"https://imdb-api.com/images/original/MV5BMTgzNjA1MjY2M15BMl5BanBnXkFtZTYwMjgzOTk0._V1_Ratio0.7273_AL_.jpg",
				"Jean Reno"
			),
			Actor(
				"Stansfield",
				"nm0000198",
				"https://imdb-api.com/images/original/MV5BMTc3NTM4MzQ5MV5BMl5BanBnXkFtZTcwOTE4MDczNw@@._V1_Ratio0.7273_AL_.jpg",
				"Gary Oldman"
			),
			Actor(
				"Mathilda",
				"nm0000204",
				"https://imdb-api.com/images/original/MV5BMTQ3ODE3Mjg1NV5BMl5BanBnXkFtZTcwNzA4ODcxNA@@._V1_Ratio0.7273_AL_.jpg",
				"Natalie Portman"
			),
			Actor(
				"Tony",
				"nm0000732",
				"https://imdb-api.com/images/original/MV5BNTMxMjYzNzk5Nl5BMl5BanBnXkFtZTcwNzU4NDgwMw@@._V1_Ratio0.9545_AL_.jpg",
				"Danny Aiello"
			),
			Actor(
				"Malky",
				"nm0032216",
				"https://imdb-api.com/images/original/MV5BMDVlNjcxM2ItNTU2NC00YzJkLWFlMzEtZGYwMTI0MmI5ZGE4XkEyXkFqcGdeQXVyNjkyNzAzNg@@._V1_Ratio0.7273_AL_.jpg",
				"Peter Appel"
			),
			Actor(
				"1st Stansfield Man (as Willie One Blood)",
				"nm0089112",
				"https://imdb-api.com/images/original/MV5BNGU5YTdkODMtYjAxYS00NmM4LWI0MGEtZjZmNGMwYTA2Njg0XkEyXkFqcGdeQXVyNjUxMjc1OTM@._V1_Ratio2.3636_AL_.jpg",
				"Willi One Blood"
			),Actor(
				"2nd Stansfield Man",
				"nm0187199",
				"https://imdb-api.com/images/original/MV5BMTQ4MDMyODEyMF5BMl5BanBnXkFtZTgwNDI4OTQ1MjE@._V1_Ratio1.3182_AL_.jpg",
				"Don Creech"
			),
		),
		listOf(
			Company(
				"co0172053",
				"Gaumont"
			),
			Company(
				"co0008826",
				"Les Films du Dauphin"
			),
			Company(
				"co0050868",
				"Columbia Pictures"
			),
		),
		listOf(
			Country(
				"France",
				"France"
			),
			Country(
				"USA",
				"USA"
			)
		),
		listOf(
			Director(
				"nm0000108",
				"Luc Besson"
			)
		),
		listOf(
			Genre(
				"Action",
				"Action"
			),Genre(
				"Crime",
				"Crime"
			),Genre(
				"Drama",
				"Drama"
			),Genre(
				"Thriller",
				"Thriller"
			)
		),
		listOf(
			"girl",
			"hitman",
			"teenage girl",
			"older man young girl relationship",
			"neo noir"
		),
		listOf(
			Language(
				"English",
				"English"
			),
			Language(
				"Italian",
				"Italian"
			),
			Language(
				"French",
				"French"
			)
		)
	)
	
	MovieView(movie = movie)
}

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
			Image(
				painter = painterResource(id = item.icon),
				contentDescription = null,
				modifier = Modifier
					.size(10.dp)
			)
			
			Text(
				text = item.title,
				fontWeight = FontWeight.Bold,
				fontSize = TextUnit(16f, TextUnitType.Sp),
				modifier = Modifier
					.padding(start = 8.dp)
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
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navigationController: NavController) {
	val navigationBackStackEntry by navigationController.currentBackStackEntryAsState()
	val currentRoute = navigationBackStackEntry?.destination?.route
	
	Column(modifier = Modifier
		.padding(4.dp)
		.fillMaxHeight()
	) {
		LazyColumn {
			items(NavigationDrawerItem.items) { item ->
				DrawerItem(item = item, isSelected = currentRoute == item.destination) {
					navigationController.navigate(item.destination) {
						navigationController.graph.startDestinationRoute?.let { destination ->
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
