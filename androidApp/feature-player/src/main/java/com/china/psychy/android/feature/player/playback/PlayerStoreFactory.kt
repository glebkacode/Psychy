package com.china.psychy.android.feature.player.playback

import com.arkivanov.mvikotlin.core.store.Bootstrapper
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.china.psychy.android.feature.player.playback.PlayerStore.Intent
import com.china.psychy.android.feature.player.playback.PlayerStore.Label
import com.china.psychy.android.feature.player.playback.PlayerStore.State
import com.google.common.math.LongMath
import kotlin.coroutines.CoroutineContext

class PlayerStoreFactory(
    private val storeFactory: StoreFactory,
    private val player: Player,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {
    sealed interface Msg : JvmSerializable {
        data class PositionChanged(
            val position: Long,
            val duration: Long,
            val bufferPosition: Long
        ) : Msg
    }

    sealed interface Action {
        class PlaybackPositionChanged(
            val position: Long,
            val duration: Long,
            val bufferPosition: Long
        ) : Action
    }

    fun create(): PlayerStore =
        object : PlayerStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PlayerStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    inner class ExecutorImpl :
        CoroutineExecutor<Intent, Action, State, Msg, Label>(mainContext) {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
            when (intent) {
                is Intent.Start -> {
                    player.prepare()
                }

                Intent.Stop -> {
                    player.pause()
                }

                is Intent.SeekToPosition -> {
                    player.seekTo(intent.position)
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.PlaybackPositionChanged -> {
                    dispatch(Msg.PositionChanged(action.position, action.duration, action.bufferPosition))
                    publish(Label.PlayerPositionChanged(action.position, action.duration, action.bufferPosition))
                }
            }
        }
    }

    inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            player.observePositionChanged { position, duration, bufferPosition ->
                dispatch(Action.PlaybackPositionChanged(position, duration, bufferPosition))
            }
        }
    }

    object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.PositionChanged -> copy(
                    currentPosition = msg.position,
                    duration = msg.duration,
                    bufferPosition = msg.bufferPosition
                )
            }
    }
}