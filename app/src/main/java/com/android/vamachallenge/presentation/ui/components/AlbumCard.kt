package com.android.vamachallenge.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.android.vamachallenge.R
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.presentation.ui.intent.HomeIntent
import com.android.vamachallenge.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCard(
    album: Album,
    onCardClicked: (HomeIntent) -> Unit
) {
    Card(
        modifier = Modifier.aspectRatio(1f),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)),
        onClick = { onCardClicked(HomeIntent.OnAlbumClicked(album)) }
    ) {
        Box {
            AlbumImage(
                modifier = Modifier.fillMaxSize(),
                image = album.thumbnail
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = dimensionResource(id = R.dimen.padding_m))
                    .background(Color.LightGray.copy(alpha = 0.3f))
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                AlbumCardText(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_xs)),
                    text = album.name,
                    style = Typography.labelMedium
                )
                AlbumCardText(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_xs)),
                    text = album.artistName,
                    style = Typography.labelSmall
                )
            }
        }
    }
}