package com.android.vamachallenge.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.vamachallenge.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumCard(
    a: String,
    onCardClicked: () -> Unit
) {
    Card(
        modifier = Modifier.aspectRatio(1f),
        shape = RoundedCornerShape(20.dp),
        onClick = onCardClicked
    ) {
        Box {
            AlbumImage(
                modifier = Modifier.fillMaxSize(),
                image = a
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 15.dp)
                    .background(Color.LightGray.copy(alpha = 0.3f)),
                verticalArrangement = Arrangement.Center
            ) {
                AlbumCardText(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "The Death of Slim Shady (Coup De Grâce)",
                    style = Typography.labelMedium
                )
                AlbumCardText(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = "Fuerza Regida",
                    style = Typography.labelSmall
                )
            }
        }
    }
}