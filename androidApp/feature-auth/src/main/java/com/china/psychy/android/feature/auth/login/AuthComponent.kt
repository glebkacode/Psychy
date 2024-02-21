package com.china.psychy.android.feature.auth.login

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordComponent
import com.china.psychy.android.feature.auth.signin.SignInComponent
import com.china.psychy.android.feature.auth.signup.SignUpComponent

interface AuthComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class SignInChild(val component: SignInComponent) : Child()
        class SignUpChild(val component: SignUpComponent) : Child()
        class ForgotPasswordChild(val component: ForgotPasswordComponent) : Child()
    }

    sealed class Output {
        data object OpenLk : Output()
    }
}