package com.china.psychy.android.feature.player.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.china.psychy.android.feature.player.controls.root.ControlsComponent
import com.china.psychy.android.feature.player.controls.root.ControlsComponentImpl
import com.china.psychy.android.feature.player.playback.ExoPlayerWrapper
import com.china.psychy.android.feature.player.playback.PlaybackComponent
import com.china.psychy.android.feature.player.playback.PlaybackComponentImpl
import kotlin.coroutines.CoroutineContext

class RootPlayerComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    player: ExoPlayerWrapper,
    mainContext: CoroutineContext,
    ioContext: CoroutineContext,
) : RootPlayerComponent, ComponentContext by componentContext {

    override val playbackComponent: PlaybackComponent = PlaybackComponentImpl(
        componentContext = componentContext,
        storeFactory = storeFactory,
        player = player,
        mainContext = mainContext,
        ioContext = ioContext,
        output = ::onPlaybackOutput
    )

    private fun onPlaybackOutput(event: PlaybackComponent.Ouput) {
        when (event) {
            is PlaybackComponent.Ouput.PositionChanged -> controlsComponent.changePosition(
                position = event.position,
                duration = event.duration,
                bufferPosition = event.bufferPosition
            )
        }
    }

    override val controlsComponent: ControlsComponent = ControlsComponentImpl(
        componentContext = componentContext,
        storeFactory = storeFactory,
        mainContext = mainContext,
        ioContext = ioContext,
        output = ::onControlOutput
    )

    private fun onControlOutput(output: ControlsComponent.Output) {
        when (output) {
            is ControlsComponent.Output.SeekToPosition -> {
                playbackComponent.playbackSeekTo(output.position)
            }

            ControlsComponent.Output.StartPlay -> {
                playbackComponent.playbackStart()
            }

            ControlsComponent.Output.StopPlay -> {
                playbackComponent.playbackStop()
            }
        }
    }
}