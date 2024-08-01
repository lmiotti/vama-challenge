package com.android.vamachallenge.presentation.ui.state

import com.android.vamachallenge.domain.model.Album

data class HomeState(
    val isLoading: Boolean = false,
    val albums: List<Album> = emptyList(),
    val error: String? = null,
)