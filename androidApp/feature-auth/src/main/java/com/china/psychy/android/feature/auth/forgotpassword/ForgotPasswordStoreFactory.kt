package com.china.psychy.android.feature.auth.forgotpassword

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordStore.Intent
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordStore.Label
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordStore.State
import com.china.psychy.feature.auth.domain.forgotpassword.ForgotPasswordUseCase
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ForgotPasswordStoreFactory(
    private val storeFactory: StoreFactory,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {
    fun create(): ForgotPasswordStore =
        object : ForgotPasswordStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ForgotPasswordStore",
            initialState = State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    sealed interface Msg : JvmSerializable {
        data class EmailChanged(val text: String) : Msg
    }

    inner class ExecutorImpl : CoroutineExecutor<Intent, Nothing, State, Msg, Label>(mainContext) {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
            when (intent) {
                Intent.ApplyRecoveryPassword -> sendRecoveryCode(getState().email)
                is Intent.EmailChanged -> checkEmail(intent.text)
                Intent.ComeBack -> comeBack()
            }
        }

        private fun checkEmail(text: String) {
            dispatch(Msg.EmailChanged(text))
        }

        private fun sendRecoveryCode(email: String) {
            scope.launch(ioContext) {
                forgotPasswordUseCase(email)
                publish(Label.NavigateBack)
            }
        }

        private fun comeBack() {
            publish(Label.NavigateBack)
        }
    }

    object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.EmailChanged -> copy(email = msg.text)
            }
    }
}