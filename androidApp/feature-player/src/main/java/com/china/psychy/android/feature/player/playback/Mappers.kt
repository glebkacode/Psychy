package com.china.psychy.android.feature.player.playback

internal val addLabelToOutput: (PlayerStore.Label) -> PlaybackComponent.Ouput =
    { label ->
        when (label) {
            is PlayerStore.Label.PlayerPositionChanged -> PlaybackComponent.Ouput.PositionChanged(
                label.position,
                label.duration,
                label.bufferPosition
            )
        }
    }