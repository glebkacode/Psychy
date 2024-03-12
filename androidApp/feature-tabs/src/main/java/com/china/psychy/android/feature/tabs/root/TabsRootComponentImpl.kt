package com.china.psychy.android.feature.tabs.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.android.feature.tabs.fims.FilmsTabComponentImpl
import com.china.psychy.android.feature.tabs.main.tab.MainTabComponentImpl
import com.china.psychy.android.feature.tabs.profile.ProfileTabComponentImpl
import com.china.psychy.android.feature.tabs.root.TabsRootComponent.Child
import com.china.psychy.android.feature.tabs.serials.SerialsTabComponentImpl
import com.china.psychy.android.feature.tabs.tv.TvTabComponentImpl
import com.china.psychy.android.feature.tabs.voddetail.VodDetailComponent
import kotlinx.serialization.Serializable

class TabsRootComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val coreDispatcher: CoreDispatcher,
    private val openPlayer: () -> Unit,
    private val openPurchase: () -> Unit
) : ComponentContext by componentContext, TabsRootComponent {

    private val navigation = StackNavigation<Config>()

    private val stack =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Main,
            childFactory = ::child,
        )
    override val childStack: Value<ChildStack<*, Child>> = stack

    override fun onMainTabSelected() {
        navigation.bringToFront(Config.Main)
    }

    override fun onTvTabsSelected() {
        navigation.bringToFront(Config.Tv)
    }

    override fun onFilmsTabSelected() {
        navigation.bringToFront(Config.Films)
    }

    override fun onSerialTabSelected() {
        navigation.bringToFront(Config.Serials)
    }

    override fun onProfileTabSelected() {
        navigation.bringToFront(Config.Profile)
    }

    override fun onPurchasedCompleted() {
        (stack.active.instance as? Child.MainChild)?.component?.onPurchaseCompleted()
    }

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when(config) {
            is Config.Main -> Child.MainChild(
                MainTabComponentImpl(
                    componentContext = componentContext,
                    storeFactory = storeFactory,
                    coreDispatcher = coreDispatcher,
                    openPlayer = { openPlayer() },
                    openPurchase = { openPurchase() }
                )
            )
            is Config.Tv -> Child.TvChild(TvTabComponentImpl(componentContext = componentContext))
            is Config.Films -> Child.FilmsChild(FilmsTabComponentImpl(componentContext = componentContext))
            is Config.Serials -> Child.SerialChild(SerialsTabComponentImpl(componentContext = componentContext))
            is Config.Profile -> Child.ProfileChild(ProfileTabComponentImpl(componentContext = componentContext))
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Main : Config
        @Serializable
        data object Tv : Config
        @Serializable
        data object Films : Config
        @Serializable
        data object Serials : Config
        @Serializable
        data object Profile : Config
    }
}