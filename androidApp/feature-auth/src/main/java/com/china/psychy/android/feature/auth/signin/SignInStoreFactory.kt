package com.china.psychy.android.feature.auth.signin

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import kotlin.coroutines.CoroutineContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.china.psychy.android.feature.auth.signin.SignInStore.State
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.china.psychy.android.feature.auth.signin.SignInStore.ErrorType
import com.china.psychy.android.feature.auth.signin.SignInStore.Intent
import com.china.psychy.android.feature.auth.signin.SignInStore.Label
import com.china.psychy.feature.auth.domain.forgotpassword.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.loginuser.LoginUserUseCase
import com.china.psychy.feature.auth.model.User
import kotlinx.coroutines.launch
import java.net.UnknownHostException

private const val PASSWORD_MIN_LENGTH = 4
private const val PASSWORD_MAX_LENGTH = 30

class SignInStoreFactory(
    private val storeFactory: StoreFactory,
    private val loginUserUseCase: LoginUserUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {

    sealed interface Msg : JvmSerializable {
        data class EmailChanged(val text: String) : Msg
        data class EmailInvalid(val text: String) : Msg
        data class PasswordChanged(val text: String) : Msg
        data class PasswordInvalid(val text: String) : Msg
        data object InternetConnectionFailed : Msg
    }

    fun create(): SignInStore =
        object : SignInStore, Store<Intent, State, Label> by storeFactory.create(
            name = "SignInStore",
            initialState = State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    inner class ExecutorImpl : CoroutineExecutor<Intent, Nothing, State, Msg, Label>(mainContext) {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
            when (intent) {
                is Intent.EmailChanged -> changeEmail(intent.text)
                is Intent.PasswordChanged -> changePassword(intent.text)
                is Intent.LoginClicked -> loginUser(getState())
                is Intent.ForgotPasswordClicked -> forgotPassword(getState().email)
                Intent.NoAccountClicked -> registerNewUser()
            }
        }

        private fun changeEmail(text: String) {
            if (text.isNotEmpty() && text.length > 4) {
                dispatch(Msg.EmailChanged(text))
            } else {
                dispatch(Msg.EmailInvalid(text))
            }
        }

        private fun changePassword(text: String) {
            if (text.length in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH) {
                dispatch(Msg.PasswordChanged(text))
            } else {
                dispatch(Msg.PasswordInvalid(text))
            }
        }

        private fun loginUser(state: State) {
            scope.launch(ioContext) {
                if (state.isEmailValid && state.isPasswordValid) {
                    runCatching {
                        loginUserUseCase(User(state.email, state.password))
                    }.onSuccess {
                        publish(Label.OpenLk)
                    }.onFailure { throwable ->
                        if (throwable is UnknownHostException) {
                            dispatch(Msg.InternetConnectionFailed)
                        }
                    }
                }
            }
        }

        private fun forgotPassword(email: String) {
            scope.launch(ioContext) {
                forgotPasswordUseCase(email)
                publish(Label.RecoveryPassword)
            }
        }

        private fun registerNewUser() {
            publish(Label.RegisterNewUser)
        }
    }

    object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.EmailChanged -> copy(
                    email = msg.text,
                    isEmailValid = true,
                    errorType = ErrorType.None
                )

                is Msg.EmailInvalid -> copy(
                    email = msg.text,
                    isEmailValid = false,
                    errorType = ErrorType.None
                )

                is Msg.PasswordChanged -> copy(
                    password = msg.text,
                    isPasswordValid = true,
                    errorType = ErrorType.None
                )

                is Msg.PasswordInvalid -> copy(
                    password = msg.text,
                    isPasswordValid = false,
                    errorType = ErrorType.None
                )

                is Msg.InternetConnectionFailed -> copy(errorType = ErrorType.Network)
            }
    }
}