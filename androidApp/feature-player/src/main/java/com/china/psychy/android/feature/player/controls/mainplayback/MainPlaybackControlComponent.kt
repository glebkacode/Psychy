package com.china.psychy.android.feature.player.controls.mainplayback

import kotlinx.coroutines.flow.Flow

interface MainPlaybackControlComponent {
    val models: Flow<Model>
    fun onPlayClicked()
    fun onPauseClicked()
    fun onOutsideClicked()
    fun seekTo(progress: Float)
    fun playbackChanged(
        position: Long,
        duration: Long,
        bufferPosition: Long
    )
    data class Model(
        val startTime: String = "",
        val endTime: String = "",
        val progressPosition: Float = 0f,
        val bufferPosition: Float = 0f,
        val isControlsVisible: Boolean = true,
        val isPlay: Boolean = false
    )
    sealed class Output {
        data object StartPlayback : Output()
        data object StopPlayback : Output()
        data class SeekToPosition(val position: Long) : Output()
    }
}