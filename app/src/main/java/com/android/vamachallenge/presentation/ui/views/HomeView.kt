package com.android.vamachallenge.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.vamachallenge.presentation.ui.components.AlbumCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(onCardClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Top music in the US") })
        }
    ) {
        HomeViewContent(
            paddingValues = it,
            onCardClicked = onCardClicked
        )
    }
}


val a = listOf(
    "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/92/9f/69/929f69f1-9977-3a44-d674-11f70c852d1b/24UMGIM36186.rgb.jpg/100x100bb.jpg",
    "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/24/95/0f/24950f19-f5dd-696b-8295-df4f780a0d9a/196872326017.jpg/100x100bb.jpg",
    "https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/8b/2c/ce/8b2cced1-ef53-ae9f-df26-5c5d8ad0009e/24UMGIM70968.rgb.jpg/100x100bb.jpg"
)
@Composable
fun HomeViewContent(
    paddingValues: PaddingValues,
    onCardClicked: () -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(a.size) {
            AlbumCard(
                a = a[it],
                onCardClicked = onCardClicked
            )
        }
    }
}
