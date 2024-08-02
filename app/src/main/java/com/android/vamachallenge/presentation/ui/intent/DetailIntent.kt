package com.android.vamachallenge.presentation.ui.intent

sealed class DetailIntent {

    data class OnOpenItunesClicked(val url: String): DetailIntent()
}