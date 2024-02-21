package com.china.psychy.android.feature.auth.forgotpassword

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordStore.Intent
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordStore.Label
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordStore.State

interface ForgotPasswordStore : Store<Intent, State, Label> {

    sealed class Intent : JvmSerializable {
        data class EmailChanged(val text: String) : Intent()
        data object ApplyRecoveryPassword : Intent()
        data object ComeBack : Intent()
    }

    sealed class Label : JvmSerializable {
        data object NavigateBack : Label()
    }

    data class State(
        val email: String = ""
    )
}