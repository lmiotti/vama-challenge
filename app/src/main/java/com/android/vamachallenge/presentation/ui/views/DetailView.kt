package com.android.vamachallenge.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.presentation.ui.components.AlbumImage
import com.android.vamachallenge.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(album: Album) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Titulo del album")
            })
        }
    ) {
        DetailViewContent(it, album)
    }
}

@Composable
fun DetailViewContent(
    paddingValues: PaddingValues,
    album: Album
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
        ) {
            AlbumImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                image = album.thumbnail
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = album.name,
            modifier = Modifier.padding(horizontal = 30.dp),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = album.artistName,
            color = Color.Black,
            style = Typography.titleMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Genre(s)",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(2.dp))
        album.genres.forEach {
            Text(
                text = it,
                style = Typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Release date",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyMedium
        )
        Text(
            text = album.releaseDate,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = album.copyright,
            style = Typography.bodySmall
        )
    }
}