package com.china.psychy.android.feature.player.controls.mainplayback

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainPlaybackControlUi(component: MainPlaybackControlComponent) {
    val models by component.models.collectAsState(initial = MainPlaybackControlComponent.Model())
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    component.onOutsideClicked()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (models.isControlsVisible) {
            PlayerHeader(
                title = "Джентельмены",
                modifier = Modifier.align(Alignment.TopCenter),
                onSettingsClicked = { component.onSettingsClicked() }
            )
            PlayPauseUi(
                isPlay = models.isPlay,
                onPlayClicked = { component.onPlayClicked() },
                onPauseClicked = { component.onPauseClicked() }
            )
            if (models.isSidePanelOpened) {
                SidePanel(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .width(328.dp)
                        .fillMaxHeight()
                )
            }
            ProgressUi(
                modifier = Modifier.align(Alignment.BottomCenter),
                startTime = models.startTime,
                endTime = models.endTime,
                progressPosition = models.progressPosition,
                bufferPosition = models.bufferPosition,
                onSeekTo = { progress -> component.seekTo(progress) }
            )
        }
    }
}

@Preview
@Composable
fun MainPlaybackControlUiPreview() {
    MaterialTheme {
        MainPlaybackControlUi(
            MainPlaybackControlComponentPreview()
        )
    }
}