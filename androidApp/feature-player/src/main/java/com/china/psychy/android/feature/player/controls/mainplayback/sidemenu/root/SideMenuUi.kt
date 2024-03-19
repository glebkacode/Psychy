package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.root

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.audiosubtitle.AudioSubtitleSettingsUi
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.menulist.MenuListUi
import com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.videoquality.VideoQualityUi

@Composable
fun SideMenuUi(
    component: RootSideMenuComponent,
    modifier: Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier
        .fillMaxSize()
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = {
                component.onOutsideClicked()
            }
        )) {
        Children(
            stack = component.childStack,
            modifier = Modifier
                .background(Color.White)
                .align(Alignment.TopEnd)
                .width(400.dp)
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
}