package com.china.psychy.android.feature.player.playback

import androidx.media3.ui.PlayerView

interface ExoPlayerWrapper : Player {
    fun attach(playerView: PlayerView)
}