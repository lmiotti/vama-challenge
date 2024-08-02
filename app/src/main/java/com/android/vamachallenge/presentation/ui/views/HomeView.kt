package com.android.vamachallenge.presentation.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.presentation.ui.components.AlbumCard
import com.android.vamachallenge.presentation.ui.intent.HomeIntent
import com.android.vamachallenge.presentation.ui.viewmodel.HomeViewModel
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    onCardClicked: (Album) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Top music in the US") })
        }
    ) {
        HomeViewContent(
            viewModel = viewModel,
            paddingValues = it,
            handleIntent = {
                when(it) {
                    is HomeIntent.OnAlbumClicked -> onCardClicked(it.album)
                }
            }
        )
    }
}

@Composable
fun HomeViewContent(
    viewModel: HomeViewModel,
    paddingValues: PaddingValues,
    handleIntent: (HomeIntent) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycle = LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.showError.collectLatest {
                if (it) Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    LazyVerticalGrid(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (state.isLoading) {
            items(10) {
                Card(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .shimmer(),
                    shape = RoundedCornerShape(20.dp),
                ) {}
            }
        } else {
            items(state.albums.size) {
                AlbumCard(
                    album = state.albums[it],
                    onCardClicked = handleIntent
                )
            }
        }
    }
}
