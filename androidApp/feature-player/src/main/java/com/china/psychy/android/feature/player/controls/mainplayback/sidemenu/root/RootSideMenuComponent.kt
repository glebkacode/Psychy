package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.audiosubtitle.AudioSubtitleSettingsComponent
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.menulist.SideMenuListComponent
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.videoquality.VideoQualityComponent

interface RootSideMenuComponent {
    val childStack: Value<ChildStack<*, Child>>
    sealed class Child {
        data class MenuList(val component: SideMenuListComponent) : Child()
        data class VideoQualitySettings(val component: VideoQualityComponent) : Child()
        data class AudioSubtitleSettings(val component: AudioSubtitleSettingsComponent) : Child()
    }
}