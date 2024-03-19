package com.china.psychy.android.feature.player.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.china.psychy.android.feature.player.controls.root.RootControlsComponent
import com.china.psychy.android.feature.player.controls.root.RootControlsComponentImpl
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
            is PlaybackComponent.Ouput.PositionChanged -> rootControlsComponent.changePosition(
                position = event.position,
                duration = event.duration,
                bufferPosition = event.bufferPosition
            )
        }
    }

    override val rootControlsComponent: RootControlsComponent = RootControlsComponentImpl(
        componentContext = componentContext,
        storeFactory = storeFactory,
        mainContext = mainContext,
        ioContext = ioContext,
        output = ::onControlOutput
    )

    private fun onControlOutput(output: RootControlsComponent.Output) {
        when (output) {
            is RootControlsComponent.Output.SeekToPosition -> {
                playbackComponent.playbackSeekTo(output.position)
            }

            RootControlsComponent.Output.StartPlay -> {
                playbackComponent.playbackStart()
            }

            RootControlsComponent.Output.StopPlay -> {
                playbackComponent.playbackStop()
            }
        }
    }
}