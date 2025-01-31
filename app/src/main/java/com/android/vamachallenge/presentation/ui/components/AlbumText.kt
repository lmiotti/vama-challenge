package com.android.vamachallenge.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.android.vamachallenge.R

@Composable
fun AlbumCardText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle
) {
    Text(
        modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_m)),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color.Black,
        text = text,
        style = style
    )
}
