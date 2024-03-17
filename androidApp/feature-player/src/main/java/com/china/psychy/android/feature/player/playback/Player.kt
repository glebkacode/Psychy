package com.china.psychy.android.feature.player.playback

interface Player {
    fun prepare()
    fun seekTo(position: Long)
    fun pause()
    fun destroy()
    fun observePositionChanged(positionChanged: (Long, Long, Long) -> Unit)
}