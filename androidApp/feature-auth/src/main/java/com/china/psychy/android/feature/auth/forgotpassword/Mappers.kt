package com.china.psychy.android.feature.auth.forgotpassword

internal val addLabelToOutput: (ForgotPasswordStore.Label) -> ForgotPasswordComponent.Output =
    { label ->
        when(label) {
            ForgotPasswordStore.Label.NavigateBack -> ForgotPasswordComponent.Output.Back
        }
    }