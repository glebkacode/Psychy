package com.china.psychy.android.feature.player.controls.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlUi

@Composable
fun ControlsUi(component: ControlsComponent) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        MainPlaybackControlUi(component = component.mainPlaybackControlComponent)
    }
}