package com.android.vamachallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.vamachallenge.presentation.navigation.Routes
import com.android.vamachallenge.presentation.ui.views.DetailView
import com.android.vamachallenge.presentation.views.HomeView

@Composable
fun NavManager() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeView()
        }
        composable(Routes.Detail.route) {
            DetailView()
        }
    }
}