package com.china.psychy.android.feature.player.controls.mainplayback

import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlComponent.Model
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.root.RootSideMenuComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainPlaybackControlComponentPreview : MainPlaybackControlComponent {
    override val models: Flow<Model> = MutableStateFlow(
        Model(
            startTime = "01:00",
            endTime = "180:00",
            progressPosition = 500f,
            bufferPosition = 1000f
        )
    )
    override val sideMenuComponent: RootSideMenuComponent
        get() = TODO("Not yet implemented")

    override fun onPlayClicked() {}

    override fun onPauseClicked() {}
    override fun onOutsideClicked() {}
    override fun onSettingsClicked() {}
    override fun onSettingsClosed() {
        TODO("Not yet implemented")
    }

    override fun seekTo(progress: Float) {}

    override fun playbackChanged(position: Long, duration: Long, bufferPosition: Long) {}
}