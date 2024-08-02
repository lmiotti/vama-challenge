package com.android.vamachallenge.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.android.vamachallenge.R
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.presentation.ui.components.AlbumImage
import com.android.vamachallenge.presentation.ui.intent.DetailIntent
import com.android.vamachallenge.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(album: Album) {
    val uriHandler = LocalUriHandler.current

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
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    ) {
        DetailScreenContent(it, album) {
            when(it) {
                is DetailIntent.OnOpenItunesClicked -> {
                    uriHandler.openUri(it.url)
                }
            }
        }
    }
}

@Composable
fun DetailScreenContent(
    paddingValues: PaddingValues,
    album: Album,
    handleIntent: (DetailIntent) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(paddingValues)
            .padding(horizontal = dimensionResource(id = R.dimen.padding_xl))
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Card(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)),
        ) {
            AlbumImage(
                modifier = Modifier.fillMaxWidth(),
                image = album.thumbnail
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_l)))
        Text(
            text = album.name,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_xl)),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_m)))
        Text(
            text = album.artistName,
            color = MaterialTheme.colorScheme.secondary,
            style = Typography.titleMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_m)))
        Text(
            text = stringResource(id = R.string.detail_genre),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_s)))
        album.genres.forEach {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.secondary,
                style = Typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_m)))
        Text(
            text = stringResource(id = R.string.detail_release_date),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyMedium
        )
        Text(
            text = album.releaseDate,
            color = MaterialTheme.colorScheme.secondary,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            onClick =  { handleIntent(DetailIntent.OnOpenItunesClicked(album.url)) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Text(
                text = stringResource(id = R.string.detail_open_itunes),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_l)))
        Text(
            text = album.copyright,
            color = MaterialTheme.colorScheme.secondary,
            style = Typography.bodySmall
        )
    }
}