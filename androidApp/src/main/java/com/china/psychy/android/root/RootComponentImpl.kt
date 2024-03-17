package com.china.psychy.android.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.android.feature.auth.login.AuthComponent
import com.china.psychy.android.feature.auth.login.AuthComponentImpl
import com.china.psychy.android.feature.player.playback.ExoPlayerWrapper
import com.china.psychy.android.feature.player.playback.Player
import com.china.psychy.android.feature.player.root.RootPlayerComponentImpl
import com.china.psychy.android.feature.tabs.root.TabsRootComponentImpl
import com.china.psychy.android.root.RootComponent.Child
import com.china.psychy.feature.auth.domain.forgotpassword.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.loginuser.LoginUserUseCase
import com.china.psychy.feature.auth.domain.registeruser.RegisterUserUseCase
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val coreDispatcher: CoreDispatcher,
    private val player: ExoPlayerWrapper
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Player,
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
                    registerUserUseCase = registerUserUseCase,
                    forgotPasswordUseCase = forgotPasswordUseCase,
                    coreDispatcher = coreDispatcher,
                    output = ::onAuthOutput
                )
            )
            Config.RootTabs -> Child.TabsRootChild(
                TabsRootComponentImpl(
                    componentContext = componentContext,
                    storeFactory = storeFactory,
                    coreDispatcher = coreDispatcher,
                    openPlayer = { navigation.push(Config.Player) },
                )
            )
            Config.Player -> Child.PlayerChild(
                RootPlayerComponentImpl(
                    componentContext = componentContext,
                    storeFactory = storeFactory,
                    player = player,
                    mainContext = coreDispatcher.main(),
                    ioContext = coreDispatcher.io()
                )
            )
        }

    private fun onPurchaseCompleted() {
        navigation.pop {
            (stack.active.instance as? Child.TabsRootChild)?.component?.onPurchasedCompleted()
        }
    }

    private fun onAuthOutput(output: AuthComponent.Output) {
        when(output) {
            AuthComponent.Output.OpenLk -> navigation.push(Config.RootTabs)
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Auth : Config
        @Serializable
        data object RootTabs : Config
        @Serializable
        data object Player : Config
    }
}