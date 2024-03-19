package com.china.psychy.android.feature.player.controls.mainplayback

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlStore.Intent
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlStore.Label
import com.china.psychy.android.feature.player.controls.mainplayback.MainPlaybackControlStore.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainPlaybackControlStoreFactory(
    private val storeFactory: StoreFactory,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {

    sealed interface Msg : JvmSerializable {
        data class SeekToPosition(
            val position: Long
        ) : Msg
        data class PositionChanged(
            val position: Long,
            val duration: Long,
            val bufferPosition: Long
        ) : Msg
        data object ShowControls : Msg
        data object HideControls : Msg
        data object Play : Msg
        data object Pause : Msg
        data object SettingsSelected : Msg
    }

    fun create(): MainPlaybackControlStore =
        object : MainPlaybackControlStore, Store<Intent, State, Label> by storeFactory.create(
            name = "MainPlaybackControlStore",
            initialState = State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    inner class ExecutorImpl :
        CoroutineExecutor<Intent, Nothing, State, Msg, Label>(mainContext) {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
            when (intent) {
                is Intent.SeekToPosition -> onSeekToPosition(intent.progress)
                is Intent.PlaybackPositionChanged -> onPlaybackPositionChanged(
                    intent.position,
                    intent.duration,
                    intent.bufferPosition
                )
                Intent.Play -> onPlay()
                Intent.Pause -> onPause()
                Intent.OutsideSelected -> onOutsideSelected()
                Intent.SettingsSelected -> onSettingsSelected()
            }
        }

        private fun onSeekToPosition(
            progress: Float
        ) {
            publish(Label.SeekToPosition(position = progress.toInt() * 1000L))
            dispatch(Msg.SeekToPosition(position = progress.toInt() * 1000L))
        }

        private fun onPlaybackPositionChanged(
            position: Long,
            duration: Long,
            bufferPosition: Long
        ) {
            dispatch(Msg.PositionChanged(position, duration, bufferPosition))
        }

        private fun onPlay() {
            publish(Label.StartPlayback)
            dispatch(Msg.Play)
            scope.launch(ioContext) {
                delay(3000)
                withContext(mainContext) {
                    dispatch(Msg.HideControls)
                }
            }
        }

        private fun onPause() {
            publish(Label.StopPlayback)
            dispatch(Msg.Pause)
        }

        private fun onOutsideSelected() {
            dispatch(Msg.ShowControls)
        }

        private fun onSettingsSelected() {
            dispatch(Msg.SettingsSelected)
        }
    }

    object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.PositionChanged -> copy(
                    currentPosition = msg.position,
                    duration = msg.duration
                )
                is Msg.SeekToPosition -> copy(
                    currentPosition = msg.position
                )
                Msg.HideControls -> copy(
                    isControlsVisible = false
                )
                Msg.ShowControls -> copy(
                    isControlsVisible = true,
                    isSettingsSelected = false
                )
                Msg.Play -> copy(
                    isPlay = true
                )
                Msg.Pause -> copy(
                    isPlay = false
                )
                Msg.SettingsSelected -> copy(
                    isSettingsSelected = true
                )
            }
        }
    }
}