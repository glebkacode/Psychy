package com.china.psychy.android.feature.player.playback

interface PlaybackComponent {
    val player: ExoPlayerWrapper
    fun playbackStart()
    fun playbackStop()
    fun playbackSeekTo(position: Long)
    sealed class Ouput {
        data class PositionChanged(
            val position: Long,
            val duration: Long,
            val bufferPosition: Long
        ) : Ouput()
    }
}