package com.china.psychy.android.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.android.feature.auth.login.AuthComponent
import com.china.psychy.android.feature.auth.login.AuthComponentImpl
import com.china.psychy.android.feature.lk.LkComponentImpl
import com.china.psychy.android.root.RootComponent.Child
import com.china.psychy.feature.auth.domain.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.LoginUserUseCase
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val loginUserUseCase: LoginUserUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val coreDispatcher: CoreDispatcher,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Auth,
        childFactory = ::child
    )
    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.Auth -> Child.AuthChild(
                AuthComponentImpl(
                    componentContext = componentContext,
                    storeFactory = storeFactory,
                    loginUserUseCase = loginUserUseCase,
                    forgotPasswordUseCase = forgotPasswordUseCase,
                    coreDispatcher = coreDispatcher,
                    output = ::onAuthOutput
                )
            )
            Config.Lk -> Child.LkChild(LkComponentImpl())
        }

    private fun onAuthOutput(output: AuthComponent.Output) {
        when(output) {
            AuthComponent.Output.OpenLk -> navigation.push(Config.Lk)
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Auth : Config
        @Serializable
        data object Lk : Config
    }
}