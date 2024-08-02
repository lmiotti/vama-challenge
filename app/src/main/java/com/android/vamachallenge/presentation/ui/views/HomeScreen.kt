package com.android.vamachallenge.presentation.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.android.vamachallenge.R
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.presentation.ui.components.AlbumCard
import com.android.vamachallenge.presentation.ui.intent.HomeIntent
import com.android.vamachallenge.presentation.ui.state.HomeState
import com.android.vamachallenge.presentation.ui.viewmodel.HomeViewModel
import com.android.vamachallenge.ui.theme.Typography
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCardClicked: (Album) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.home_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    ) {
        HomeScreenContent(
            viewModel = viewModel,
            paddingValues = it,
            handleIntent = {
                when(it) {
                    is HomeIntent.OnAlbumClicked -> onCardClicked(it.album)
                    else -> viewModel.handleIntent(it)
                }
            }
        )
    }
}

@Composable
fun HomeScreenContent(
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

    if (state.albums.isEmpty() && !state.error.isNullOrEmpty()) {
        HomeEmptyScreen(handleIntent)
    } else {
        HomeScreenGrid(paddingValues = paddingValues, state = state, handleIntent)
    }
}

@Composable
fun HomeScreenGrid(
    paddingValues: PaddingValues,
    state: HomeState,
    handleIntent: (HomeIntent) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(paddingValues)
            .padding(horizontal = dimensionResource(id = R.dimen.padding_m))
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.home_grid_space)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.home_grid_space))
    ) {
        if (state.isLoading) {
            items(10) {
                Card(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .shimmer(),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)),
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

@Composable
fun HomeEmptyScreen(
    handleIntent: (HomeIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.ic_internet_connection),
            contentDescription = stringResource(id = R.string.home_no_internet),
            modifier = Modifier.size(dimensionResource(id = R.dimen.home_empty_width)),
            colorFilter = ColorFilter.tint(Color.White)
        )
        Text(
            text = stringResource(id = R.string.home_error_occurred),
            modifier = Modifier.width(dimensionResource(id = R.dimen.home_empty_width)),
            color = MaterialTheme.colorScheme.secondary,
            style = Typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.home_try_again),
            modifier = Modifier.width(dimensionResource(id = R.dimen.home_empty_width)),
            color = MaterialTheme.colorScheme.secondary,
            style = Typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_l)))
        Button(
            onClick = { handleIntent(HomeIntent.OnRetryClicked) },
            modifier = Modifier.width(dimensionResource(id = R.dimen.home_empty_width)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(stringResource(id = R.string.home_retry_button))
        }
    }
}
