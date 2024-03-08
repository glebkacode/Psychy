package com.china.psychy.android.feature.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlayerScreen(
    component: PlayerComponent,
    modifier: Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Player")
    }
}