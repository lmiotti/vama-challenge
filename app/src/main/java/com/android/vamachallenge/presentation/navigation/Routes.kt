package com.android.vamachallenge.presentation.navigation

sealed class Routes(val route: String) {
    object Home: Routes("Home")
    object Detail: Routes("Detail")
}