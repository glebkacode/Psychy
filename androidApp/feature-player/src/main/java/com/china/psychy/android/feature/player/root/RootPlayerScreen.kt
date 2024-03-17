package com.china.psychy.android.feature.player.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.china.psychy.android.feature.player.controls.root.ControlsUi
import com.china.psychy.android.feature.player.playback.PlaybackUi

@Composable
fun RootPlayerScreen(
    component: RootPlayerComponent,
    modifier: Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PlaybackUi(component = component.playbackComponent)
        ControlsUi(component = component.controlsComponent)
    }
}