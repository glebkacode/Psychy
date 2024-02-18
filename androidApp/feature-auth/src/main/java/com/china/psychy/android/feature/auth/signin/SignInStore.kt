package com.china.psychy.android.feature.auth.signin

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.china.psychy.android.feature.auth.signin.SignInStore.State

interface SignInStore : Store<SignInStore.Intent, State, Nothing> {

    sealed class Intent : JvmSerializable {
        data class EmailChanged(val text: String) : Intent()
        data class PasswordChanged(val text: String) : Intent()
        data object ForgotPasswordClicked : Intent()
        data object LoginClicked : Intent()
    }

    data class State(
        val email: String = "",
        val isEmailValid: Boolean = false,
        val password: String = "",
        val isPasswordValid: Boolean = false
    ) : JvmSerializable
}