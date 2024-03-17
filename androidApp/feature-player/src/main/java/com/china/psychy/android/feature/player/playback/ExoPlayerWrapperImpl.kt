package com.china.psychy.android.feature.player.playback

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@UnstableApi
class ExoPlayerWrapperImpl(
    private val context: Context
) : ExoPlayerWrapper {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val exoPlayer = ExoPlayer.Builder(context).build()
    private var positionChanged: ((Long, Long, Long) -> Unit)? = null

    private fun trackCurrentPosition() {
        scope.launch {
            delay(1000)
            val currentPosition = exoPlayer.currentPosition
            val duration = exoPlayer.duration
            val bufferPosition = exoPlayer.bufferedPosition
            positionChanged?.invoke(currentPosition, duration, bufferPosition)
            trackCurrentPosition()
        }
    }

    override fun attach(playerView: PlayerView) {
        playerView.player = exoPlayer
    }

    override fun prepare() {
        val mediaItem = MediaItem.fromUri("https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd")
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
        trackCurrentPosition()
    }

    override fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun destroy() {
        exoPlayer.release()
    }

    override fun observePositionChanged(positionChanged: (Long, Long, Long) -> Unit) {
        this.positionChanged = positionChanged
    }
}