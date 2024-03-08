package com.china.psychy.android.feature.tabs.voddetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VodDetailScreen(
    component: VodDetailComponent,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { component.onPlayButtonClicked() }) {
            Text("VodDetail Screen")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { component.onBuyVodClicked() }) {
            Text(text = "Buy the film")
        }
    }
}