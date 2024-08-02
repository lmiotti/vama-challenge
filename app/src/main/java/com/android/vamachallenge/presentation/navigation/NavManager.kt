package com.android.vamachallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.presentation.navigation.Routes
import com.android.vamachallenge.presentation.ui.views.DetailScreen
import com.android.vamachallenge.presentation.views.HomeScreen

@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home) {
        composable<Routes.Home> {
            HomeScreen(
                onCardClicked = { album ->
                    navController.navigate(Routes.Detail(
                        name = album.name,
                        artistName = album.artistName,
                        thumbnail = album.thumbnail,
                        genres = album.genres,
                        releaseDate = album.releaseDate,
                        copyright = album.copyright,
                        url = album.url
                    ))
                }
            )
        }
        composable<Routes.Detail>(
        ) {
            val detail: Routes.Detail = it.toRoute()
            val album = Album(
                name = detail.name,
                artistName = detail.artistName,
                thumbnail = detail.thumbnail,
                genres = detail.genres,
                releaseDate = detail.releaseDate,
                copyright = detail.copyright,
                url = detail.url
            )
            DetailScreen(album)
        }
    }
}