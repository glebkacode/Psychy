package com.china.psychy.android.feature.player.playback

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.china.psychy.android.feature.player.playback.PlayerStore.Intent
import com.china.psychy.android.feature.player.playback.PlayerStore.Label
import com.china.psychy.android.feature.player.playback.PlayerStore.State

interface PlayerStore : Store<Intent, State, Label> {

    sealed class Intent : JvmSerializable {
        data object Start : Intent()
        data object Stop : Intent()
        data class SeekToPosition(val position: Long) : Intent()
    }

    sealed class Label : JvmSerializable {
        data class PlayerPositionChanged(
            val position: Long,
            val duration: Long,
            val bufferPosition: Long
        ) : Label()
    }

    data class State(
        val currentPosition: Long = 0,
        val duration: Long = 0,
        val bufferPosition: Long = 0,
        val url: String = ""
    ) : JvmSerializable
}