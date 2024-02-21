package com.china.psychy.android.feature.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordScreen
import com.china.psychy.android.feature.auth.signin.SignInScreen
import com.china.psychy.android.feature.auth.signup.SignUpScreen

/*typealias LoginScreen = @Composable (*//*SignInController, SignUpController*//*) -> Unit*/

/*@Inject*/
@Composable
fun LoginScreen(component: AuthComponent, modifier: Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is AuthComponent.Child.SignInChild -> SignInScreen(child.component)
            is AuthComponent.Child.SignUpChild -> SignUpScreen(child.component)
            is AuthComponent.Child.ForgotPasswordChild -> ForgotPasswordScreen(child.component)
        }
    }
}