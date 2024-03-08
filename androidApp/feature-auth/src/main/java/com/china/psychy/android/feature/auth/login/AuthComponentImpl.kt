package com.china.psychy.android.feature.auth.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordComponent
import com.china.psychy.android.feature.auth.forgotpassword.ForgotPasswordComponentImpl
import com.china.psychy.android.feature.auth.login.AuthComponent.Child
import com.china.psychy.android.feature.auth.signin.SignInComponent
import com.china.psychy.android.feature.auth.signin.SignInComponentImpl
import com.china.psychy.android.feature.auth.signup.SignUpComponent
import com.china.psychy.android.feature.auth.signup.SignUpComponentImpl
import com.china.psychy.feature.auth.domain.forgotpassword.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.loginuser.LoginUserUseCase
import com.china.psychy.feature.auth.domain.registeruser.RegisterUserUseCase
import kotlinx.serialization.Serializable

class AuthComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val coreDispatcher: CoreDispatcher,
    private val output: (AuthComponent.Output) -> Unit
) : AuthComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.SignIn,
        childFactory = ::child
    )
    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.SignIn -> Child.SignInChild(
                SignInComponentImpl(
                    componentContext,
                    storeFactory,
                    loginUserUseCase,
                    forgotPasswordUseCase,
                    coreDispatcher,
                    ::onSignInOutput
                )
            )
            Config.SignUp -> Child.SignUpChild(
                SignUpComponentImpl(
                    componentContext,
                    storeFactory,
                    registerUserUseCase,
                    coreDispatcher,
                    ::onSignUpOutput
                )
            )
            Config.ForgotPassword -> Child.ForgotPasswordChild(
                ForgotPasswordComponentImpl(
                    componentContext,
                    storeFactory,
                    forgotPasswordUseCase,
                    coreDispatcher,
                    ::onForgotPasswordOutput
                )
            )
        }

    private fun onSignInOutput(output: SignInComponent.Output) {
        when(output) {
            SignInComponent.Output.OpenLk -> output(AuthComponent.Output.OpenLk)
            SignInComponent.Output.OpenForgotPassword -> navigation.push(Config.ForgotPassword)
            SignInComponent.Output.OpenSignUp -> navigation.push(Config.SignUp)
        }
    }

    private fun onSignUpOutput(output: SignUpComponent.Output) {
        when(output) {
            SignUpComponent.Output.Back -> navigation.pop()
        }
    }

    private fun onForgotPasswordOutput(output: ForgotPasswordComponent.Output) {
        when(output) {
            ForgotPasswordComponent.Output.Back -> navigation.pop()
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object SignIn : Config
        @Serializable
        data object SignUp : Config
        @Serializable
        data object ForgotPassword : Config
    }
}