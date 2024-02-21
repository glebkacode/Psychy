package com.china.psychy.android.feature.auth.forgotpassword

interface ForgotPasswordComponent {
    fun onEmailChanged(text: String)
    fun onApplyRecoveryPassword()
    fun onBackClicked()

    sealed class Output {
        data object Back : Output()
    }
}