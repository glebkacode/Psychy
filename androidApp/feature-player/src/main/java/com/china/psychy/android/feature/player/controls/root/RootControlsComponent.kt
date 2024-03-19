package com.china.psychy.android.feature.player.controls.root

import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlComponent

interface RootControlsComponent {
    val mainPlaybackControlComponent: MainPlaybackControlComponent
    fun changePosition(position: Long, duration: Long, bufferPosition: Long)
    sealed class Output {
        data object StartPlay : Output()
        data object StopPlay : Output()
        data class SeekToPosition(val position: Long) : Output()
    }
}