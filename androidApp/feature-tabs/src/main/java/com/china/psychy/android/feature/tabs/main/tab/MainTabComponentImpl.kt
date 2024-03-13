package com.china.psychy.android.feature.tabs.main.tab

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.android.feature.tabs.main.list.MainListComponentImpl
import com.china.psychy.android.feature.tabs.main.tab.MainTabComponent.Child
import com.china.psychy.android.feature.tabs.voddetail.VodDetailComponentImpl
import kotlinx.serialization.Serializable

class MainTabComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val coreDispatcher: CoreDispatcher,
    private val openPlayer: () -> Unit
) : MainTabComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.MainList,
            handleBackButton = true,
            childFactory = ::child,
        )
    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when(config) {
            is Config.MainList -> Child.MainList(
                MainListComponentImpl(
                    componentContext = componentContext,
                    openVodDetails = { navigation.push(Config.VodDetails) }
                )
            )
            is Config.VodDetails -> Child.VodDetails(
                VodDetailComponentImpl(
                    componentContext = componentContext,
                    storeFactory = storeFactory,
                    mainContext = coreDispatcher.main(),
                    ioContext = coreDispatcher.io(),
                    openPlayback = { openPlayer() }
                )
            )
        }
    }

    override fun onItemSelected() {
        navigation.push(Config.VodDetails)
    }

    override fun onPurchaseCompleted() {
        (stack.active.instance as? Child.VodDetails)?.component?.onPurchaseCompleted()
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object MainList : Config
        @Serializable
        data object VodDetails : Config
    }
}