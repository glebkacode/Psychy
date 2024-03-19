package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.audiosubtitle

import com.arkivanov.decompose.ComponentContext

class AudioSubtitleSettingsComponentImpl(
    componentContext: ComponentContext,
    val closeScreen: () -> Unit
) : AudioSubtitleSettingsComponent, ComponentContext by componentContext {
    override fun onAudioTrackSelected(trackType: AudioTrackType) {
        closeScreen()
    }
}