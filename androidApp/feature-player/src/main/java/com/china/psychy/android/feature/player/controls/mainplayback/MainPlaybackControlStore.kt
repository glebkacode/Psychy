package com.china.psychy.android.feature.player.controls.mainplayback

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlStore.State
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlStore.Intent
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlStore.Label

interface MainPlaybackControlStore : Store<Intent, State, Label> {
    sealed class Intent : JvmSerializable {
        data object Play : Intent()
        data object Pause : Intent()
        data object OutsideSelected : Intent()
        data object SettingsSelected : Intent()
        data object SettingsClosed : Intent()
        data class PlaybackPositionChanged(
            val position: Long,
            val duration: Long,
            val bufferPosition: Long
        ) : Intent()
        data class SeekToPosition(
            val progress: Float
        ) : Intent()
    }

    sealed class Label : JvmSerializable {
        data object StartPlayback : Label()
        data object StopPlayback : Label()
        data class SeekToPosition(val position: Long) : Label()
    }

    data class State(
        val currentPosition: Long = 0,
        val bufferPosition: Long = 0,
        val duration: Long = 0,
        val isControlsVisible: Boolean = true,
        val isPlay: Boolean = false,
        val isSettingsSelected: Boolean = false
    ) : JvmSerializable
}