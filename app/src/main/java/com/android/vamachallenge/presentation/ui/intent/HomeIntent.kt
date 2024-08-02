package com.android.vamachallenge.presentation.ui.intent

import com.android.vamachallenge.domain.model.Album

sealed class HomeIntent {
    data class OnAlbumClicked(val album: Album): HomeIntent()
}