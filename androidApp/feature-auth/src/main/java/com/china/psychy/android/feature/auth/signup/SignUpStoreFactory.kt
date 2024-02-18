package com.china.psychy.android.feature.auth.signup

import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.china.psychy.android.feature.auth.signup.SignUpStore.Intent
import com.china.psychy.android.feature.auth.signup.SignUpStore.State
import kotlin.coroutines.CoroutineContext

class SignUpStoreFactory(
    private val storeFactory: StoreFactory,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext
) {
    sealed interface Msg : JvmSerializable {
        data class EmailChanged(val text: String) : Msg
        data class PasswordChanged(val text: String) : Msg
    }

    fun create(): SignUpStore =
        object : SignUpStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "SignUpStore",
            initialState = State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    class ExecutorImpl : CoroutineExecutor<Intent, Nothing, State, Msg, Nothing>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
            when(intent) {
                is Intent.EmailChanged -> changeEmail(intent.text)
                is Intent.PasswordChanged -> changePassword(intent.text)
                is Intent.RegistrationClicked -> {
                    getState().apply { registerUser(email, password) }
                }
            }
        }

        private fun changeEmail(email: String) {
            // ToDo валидация email по маске
            dispatch(Msg.EmailChanged(email))
        }

        private fun changePassword(password: String) {
            // ToDo валидация паролья по маске
            dispatch(Msg.PasswordChanged(password))
        }

        private fun registerUser(email: String, password: String) {
            // ToDo запрос на бекенд для регистрации нового пользователя, помни про ошибки
        }
    }

    object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.EmailChanged -> copy(email = msg.text)
                is Msg.PasswordChanged -> copy(password = msg.text)
            }
        }
    }
}