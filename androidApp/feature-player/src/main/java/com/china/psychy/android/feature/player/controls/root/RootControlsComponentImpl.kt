package com.china.psychy.android.feature.player.controls.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlComponent
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlComponentImpl
import com.china.psychy.android.feature.player.controls.root.RootControlsComponent.Output
import kotlin.coroutines.CoroutineContext

class RootControlsComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    mainContext: CoroutineContext,
    ioContext: CoroutineContext,
    private val output: (Output) -> Unit
) : RootControlsComponent {

    override val mainPlaybackControlComponent = MainPlaybackControlComponentImpl(
        componentContext = componentContext,
        storeFactory = storeFactory,
        mainContext = mainContext,
        ioContext = ioContext,
        output = ::onMainPlaybackControlComponentOutput
    )

    private fun onMainPlaybackControlComponentOutput(output: MainPlaybackControlComponent.Output) {
        when (output) {
            is MainPlaybackControlComponent.Output.SeekToPosition -> output(Output.SeekToPosition(output.position))
            MainPlaybackControlComponent.Output.StartPlayback -> output(Output.StartPlay)
            MainPlaybackControlComponent.Output.StopPlayback -> output(Output.StopPlay)
        }
    }

    override fun changePosition(position: Long, duration: Long, bufferPosition: Long) {
        mainPlaybackControlComponent.playbackChanged(
            position = position,
            duration = duration,
            bufferPosition = bufferPosition
        )
    }
}