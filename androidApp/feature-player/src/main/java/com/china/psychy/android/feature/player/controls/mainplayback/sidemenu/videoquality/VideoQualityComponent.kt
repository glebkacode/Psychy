package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.videoquality

interface VideoQualityComponent {
    fun onQualitySelected(quality: VideoQuality)
}

enum class VideoQuality {
    Auto,
    Low,
    Medium,
    High,
    ExtraHigh
}