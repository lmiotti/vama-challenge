package com.example.bootstrap.presentation.navigation

import okhttp3.Route

sealed class Routes(val route: String) {
    object Home: Routes("Home")
    object Detail: Routes("Detail")
}