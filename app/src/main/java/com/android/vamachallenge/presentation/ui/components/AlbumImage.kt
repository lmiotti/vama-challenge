package com.android.vamachallenge.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun AlbumImage(
    modifier: Modifier = Modifier,
    image: String
) {
    val image = rememberAsyncImagePainter(model = image)

    Image(
        modifier = modifier.aspectRatio(1f),
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}