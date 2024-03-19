package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.audiosubtitle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AudioSubtitleSettingsUi(
    component: AudioSubtitleSettingsComponent
) {
    Column(modifier = Modifier.padding(16.dp)) {
        AudioSubtitleSettingsHeader()
        Spacer(modifier = Modifier.height(16.dp))
        AudioSubtitleSettingsItem(
            text = "Русский, Dolby 5.1",
            onClick = { component.onAudioTrackSelected(trackType = AudioTrackType.DOLBY) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AudioSubtitleSettingsItem(
            text = "Русский, Stereo",
            onClick = { component.onAudioTrackSelected(trackType = AudioTrackType.STEREO) }
        )
    }
}

@Composable
fun AudioSubtitleSettingsHeader() {
    Text(
        text = "Аудио и субтитры",
        fontSize = 24.sp
    )
}

@Composable
fun AudioSubtitleSettingsItem(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    )
}