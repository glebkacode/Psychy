package com.china.psychy.android.feature.player.playback

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlin.coroutines.CoroutineContext

class PlaybackComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    override val player: ExoPlayerWrapper,
    mainContext: CoroutineContext,
    ioContext: CoroutineContext,
    private val output: (PlaybackComponent.Ouput) -> Unit
) : PlaybackComponent, ComponentContext by componentContext {

    private val playerStore = PlayerStoreFactory(
        storeFactory,
        player,
        mainContext,
        ioContext
    ).create()

    init {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY, mainContext) {
            playerStore.labels.mapNotNull(addLabelToOutput) bindTo output
        }
    }

    override fun playbackStart() {
        playerStore.accept(PlayerStore.Intent.Start)
    }

    override fun playbackStop() {
        playerStore.accept(PlayerStore.Intent.Stop)
    }

    override fun playbackSeekTo(position: Long) {
        playerStore.accept(PlayerStore.Intent.SeekToPosition(position))
    }
}