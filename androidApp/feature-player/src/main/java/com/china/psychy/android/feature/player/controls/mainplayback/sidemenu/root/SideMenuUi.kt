package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.audiosubtitle.AudioSubtitleSettingsUi
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.menulist.MenuListUi
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.videoquality.VideoQualityUi

@Composable
fun SideMenuUi(
    component: RootSideMenuComponent,
    modifier: Modifier
) {
    Children(
        stack = component.childStack,
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is RootSideMenuComponent.Child.MenuList -> MenuListUi(
                component = child.component,
                modifier = modifier
            )
            is RootSideMenuComponent.Child.AudioSubtitleSettings -> AudioSubtitleSettingsUi(
                component = child.component
            )
            is RootSideMenuComponent.Child.VideoQualitySettings -> VideoQualityUi(
                component = child.component
            )
        }
    }
}