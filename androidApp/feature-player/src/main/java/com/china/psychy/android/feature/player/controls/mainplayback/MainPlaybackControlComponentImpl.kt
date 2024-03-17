package com.china.psychy.android.feature.player.controls.mainplayback

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlStore.*
import kotlinx.coroutines.flow.mapNotNull
import kotlin.coroutines.CoroutineContext

class MainPlaybackControlComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    mainContext: CoroutineContext,
    ioContext: CoroutineContext,
    private val output: (MainPlaybackControlComponent.Output) -> Unit
) : MainPlaybackControlComponent, ComponentContext by componentContext {

    /*
    * ToDo Store кажется много на себя берет, стоит разделить на отдельные store
    * */
    private val mainPlaybackControlStore = MainPlaybackControlStoreFactory(
        storeFactory,
        mainContext,
        ioContext
    ).create()
    override val models =
        mainPlaybackControlStore.states.mapNotNull { state -> addStateToModel.invoke(state) }

    init {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY, mainContext) {
            mainPlaybackControlStore.labels.mapNotNull(addLabelToOutput) bindTo output
        }
    }

    override fun onPlayClicked() {
        mainPlaybackControlStore.accept(Intent.Play)
    }

    override fun onPauseClicked() {
        mainPlaybackControlStore.accept(Intent.Pause)
    }

    override fun onOutsideClicked() {
        mainPlaybackControlStore.accept(Intent.OutsideSelected)
    }

    override fun onSettingsClicked() {
        mainPlaybackControlStore.accept(Intent.SettingsSelected)
    }

    override fun seekTo(progress: Float) {
        mainPlaybackControlStore.accept(Intent.SeekToPosition(progress))
    }

    override fun playbackChanged(position: Long, duration: Long, bufferPosition: Long) {
        mainPlaybackControlStore.accept(
            Intent.PlaybackPositionChanged(
                position,
                duration,
                bufferPosition
            )
        )
    }
}