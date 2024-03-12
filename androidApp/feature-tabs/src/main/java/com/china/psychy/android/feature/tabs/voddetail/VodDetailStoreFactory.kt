package com.china.psychy.android.feature.tabs.voddetail

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.china.psychy.android.feature.tabs.voddetail.VodDetailStore.Intent
import com.china.psychy.android.feature.tabs.voddetail.VodDetailStore.State
import kotlin.coroutines.CoroutineContext

class VodDetailStoreFactory(
    private val storeFactory: StoreFactory,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {
    sealed interface Msg : JvmSerializable {
        data object PurchaseCompleted : Msg
    }

    fun create(): VodDetailStore = object : VodDetailStore, Store<Intent, State, Nothing> by storeFactory.create(
        name = "VodDetailStore",
        initialState = State(),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl
    ) {}

    inner class ExecutorImpl :
        CoroutineExecutor<Intent, Nothing, State, Msg, Nothing>(mainContext) {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
            when (intent) {
                Intent.PurchaseCompleted -> dispatch(Msg.PurchaseCompleted)
            }
        }
    }

    object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                Msg.PurchaseCompleted -> copy(purchaseCompleted = true)
            }
    }
}