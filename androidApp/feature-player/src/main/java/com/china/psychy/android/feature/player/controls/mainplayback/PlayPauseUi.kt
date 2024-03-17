package com.china.psychy.android.feature.player.controls.mainplayback

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.china.psychy.android.feature.player.R

@Composable
fun PlayPauseUi(
    isPlay: Boolean,
    onPlayClicked: () -> Unit,
    onPauseClicked: () -> Unit
) {
    if (!isPlay) {
        IconButton(
            onClick = {
                onPlayClicked()
            },
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Start playback"
            )
        }

    }
    if (isPlay) {
        IconButton(
            onClick = {
                onPauseClicked()
            },
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = "Stop playback"
            )
        }
    }
}