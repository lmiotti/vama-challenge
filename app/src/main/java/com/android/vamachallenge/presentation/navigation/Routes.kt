package com.android.vamachallenge.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object Home: Routes()

    @Serializable
    data class Detail(
        val name: String,
        val artistName: String,
        val thumbnail: String,
        val genres: List<String> = listOf(),
        val releaseDate: String,
        val copyright: String,
        val url: String
    ): Routes()
}
