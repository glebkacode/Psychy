package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.menulist

import com.arkivanov.decompose.ComponentContext

class SideMenuListComponentImpl(
    private val componentContext: ComponentContext,
    private val openVideoQualitySettings: () -> Unit,
    private val openAudioSubtitleSettings: () -> Unit
) : SideMenuListComponent, ComponentContext by componentContext {
    override fun onVideoQualityClicked() {
        openVideoQualitySettings()
    }

    override fun onAudioSubtitleClicked() {
        openAudioSubtitleSettings()
    }
}