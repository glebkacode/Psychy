package com.china.psychy.android.feature.auth.signup

import kotlinx.coroutines.flow.Flow

interface SignUpComponent {
    val models: Flow<Model>
    fun onSignUpClicked()
    fun onEmailChanged(text: String)
    fun onPasswordChanged(text: String)
    fun onApplyButtonClicked()

    sealed class Output {
        data object Back : Output()
    }
}