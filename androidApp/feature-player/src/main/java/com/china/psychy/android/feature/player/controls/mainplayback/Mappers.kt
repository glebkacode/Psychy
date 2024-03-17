package com.china.psychy.android.feature.player.controls.mainplayback

import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlComponent.Model
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlStore.State
import com.china.psychy.android.feature.player.playback.PlaybackComponent
import com.china.psychy.android.feature.player.playback.PlayerStore
import java.util.concurrent.TimeUnit

internal val addStateToModel: (State) -> Model =
    { state ->
        var positionSec = TimeUnit.MILLISECONDS.toSeconds(state.currentPosition).toInt()
        var buffPosSec = TimeUnit.MILLISECONDS.toSeconds(state.bufferPosition).toInt()
        val durationSec = TimeUnit.MILLISECONDS.toSeconds(state.duration).toInt() / 100

        if (positionSec in 1..5 && positionSec < durationSec)
            positionSec = 0
        if (positionSec in 6 until durationSec)
            positionSec = durationSec / 100

        if (buffPosSec in 1..5 && buffPosSec < durationSec)
            buffPosSec = 0
        if (buffPosSec in 6 until durationSec)
            buffPosSec = durationSec

        Model(
            startTime = formatTimeSpan(state.currentPosition),
            endTime = formatTimeSpan(state.duration),
            progressPosition = positionSec.toFloat(),
            bufferPosition = buffPosSec.toFloat(),
            isControlsVisible = state.isControlsVisible,
            isPlay = state.isPlay,
            isSidePanelOpened = state.isSettingsSelected
        )
    }

internal fun formatTimeSpan(span: Long): String {
    if (span < 0) {
        return "00:00"
    }

    val h = (span / 1000 / 3600)
    val m = (span / 1000 / 60 % 60)
    val s = (span / 1000 % 60)

    return if (h > 0)
        String.format("%02d:%02d:%02d", h, m, s)
    else
        String.format("%02d:%02d", m, s)
}

internal val addLabelToOutput: (MainPlaybackControlStore.Label) -> MainPlaybackControlComponent.Output =
    { label ->
        when (label) {
            is MainPlaybackControlStore.Label.SeekToPosition -> MainPlaybackControlComponent.Output.SeekToPosition(label.position)
            MainPlaybackControlStore.Label.StartPlayback -> MainPlaybackControlComponent.Output.StartPlayback
            MainPlaybackControlStore.Label.StopPlayback -> MainPlaybackControlComponent.Output.StopPlayback
        }
    }