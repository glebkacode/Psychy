package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.audiosubtitle

interface AudioSubtitleSettingsComponent {
    fun onAudioTrackSelected(trackType: AudioTrackType)
}

enum class AudioTrackType {
    DOLBY,
    STEREO
}