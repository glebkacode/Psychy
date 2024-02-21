package com.china.psychy.android.feature.auth.signin

internal val statesToModel: (SignInStore.State) -> Model =
    { state ->
        Model(
            email = EmailModel(
                text = state.email,
                isValid = state.isEmailValid
            ),
            password = PasswordModel(
                text = state.password,
                isValid = state.isPasswordValid
            )
        )
    }

internal val addLabelToOutput: (SignInStore.Label) -> SignInComponent.Output =
    { label ->
        when(label) {
            SignInStore.Label.RecoveryPassword -> SignInComponent.Output.OpenForgotPassword
            SignInStore.Label.RegisterNewUser -> SignInComponent.Output.OpenSignUp
        }
    }