package com.android.vamachallenge.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.presentation.ui.components.AlbumImage
import com.android.vamachallenge.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(album: Album) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = album.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        DetailScreenContent(it, album)
    }
}

@Composable
fun DetailScreenContent(
    paddingValues: PaddingValues,
    album: Album
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(Color.Black)
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
            color = Color.White,
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = album.artistName,
            color = Color.White,
            style = Typography.titleMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Genre(s)",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(2.dp))
        album.genres.forEach {
            Text(
                text = it,
                color = Color.White,
                style = Typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Release date",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyMedium
        )
        Text(
            text = album.releaseDate,
            color = Color.White,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = album.copyright,
            color = Color.White,
            style = Typography.bodySmall
        )
    }
}