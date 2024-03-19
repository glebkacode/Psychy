package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.videoquality

import com.arkivanov.decompose.ComponentContext

class VideoQualityComponentImpl(
    componentContext: ComponentContext,
    val closeScreen: () -> Unit
) : VideoQualityComponent, ComponentContext by componentContext {
    override fun onQualitySelected(quality: VideoQuality) {
        closeScreen()
    }
}