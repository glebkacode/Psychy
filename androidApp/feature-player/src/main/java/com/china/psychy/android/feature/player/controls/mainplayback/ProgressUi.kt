package com.china.psychy.android.feature.player.controls.mainplayback

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProgressUi(
    modifier: Modifier,
    progressPosition: Float,
    bufferPosition: Float,
    startTime: String,
    endTime: String,
    onSeekTo: (Float) -> Unit
) {
    Column(modifier) {
        Row {
            Text(text = startTime)
            Text(text = "/")
            Text(text = endTime)
        }
        Slider(
            value = progressPosition,
            valueRange = 0f..100f,
            onValueChange = { progress -> onSeekTo(progress) }
        )
    }
}