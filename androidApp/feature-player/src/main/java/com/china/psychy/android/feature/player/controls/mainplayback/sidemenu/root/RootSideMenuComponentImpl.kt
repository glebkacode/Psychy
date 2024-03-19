package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.audiosubtitle.AudioSubtitleSettingsComponentImpl
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.root.RootSideMenuComponent.Child
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.menulist.SideMenuListComponentImpl
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.videoquality.VideoQualityComponentImpl
import kotlinx.serialization.Serializable

class RootSideMenuComponentImpl(
    componentContext: ComponentContext,
    val closeSideMenu: () -> Unit
) : RootSideMenuComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.MenuList,
        childFactory = ::child
    )
    override val childStack: Value<ChildStack<*, Child>> = stack
    override fun onOutsideClicked() {
        closeSideMenu()
        navigation.popTo(0)
    }

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.MenuList -> Child.MenuList(
                SideMenuListComponentImpl(
                    componentContext = componentContext,
                    openVideoQualitySettings = { navigation.push(Config.VideoQualitySettings) },
                    openAudioSubtitleSettings = { navigation.push(Config.AudioSubtitleSettings) }
                )
            )
            Config.AudioSubtitleSettings -> Child.AudioSubtitleSettings(
                AudioSubtitleSettingsComponentImpl(
                    componentContext = componentContext,
                    closeScreen = { navigation.pop() }
                )
            )
            Config.VideoQualitySettings -> Child.VideoQualitySettings(
                VideoQualityComponentImpl(
                    componentContext = componentContext,
                    closeScreen = { navigation.pop() }
                )
            )
        }


    @Serializable
    private sealed interface Config {
        @Serializable
        data object MenuList : Config

        @Serializable
        data object VideoQualitySettings : Config

        @Serializable
        data object AudioSubtitleSettings : Config
    }
}