package com.china.psychy.android.feature.player.playback

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView

@Composable
fun PlaybackUi(component: PlaybackComponent) {
    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                useController = false
                component.player.attach(this)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}