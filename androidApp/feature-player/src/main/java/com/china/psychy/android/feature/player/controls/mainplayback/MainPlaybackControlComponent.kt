package com.china.psychy.android.feature.player.controls.mainplayback

import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.root.RootSideMenuComponent
import kotlinx.coroutines.flow.Flow

interface MainPlaybackControlComponent {
    val models: Flow<Model>
    val sideMenuComponent: RootSideMenuComponent
    fun onPlayClicked()
    fun onPauseClicked()
    fun onOutsideClicked()
    fun onSettingsClicked()
    fun seekTo(progress: Float)
    fun playbackChanged(
        position: Long,
        duration: Long,
        bufferPosition: Long
    )
    // ToDo может стоит сделать model через sealed class ?
    data class Model(
        val startTime: String = "",
        val endTime: String = "",
        val progressPosition: Float = 0f,
        val bufferPosition: Float = 0f,
        val isControlsVisible: Boolean = true,
        val isPlay: Boolean = false,
        val isSidePanelOpened: Boolean = false
    )
    sealed class Output {
        data object StartPlayback : Output()
        data object StopPlayback : Output()
        data class SeekToPosition(val position: Long) : Output()
    }
}