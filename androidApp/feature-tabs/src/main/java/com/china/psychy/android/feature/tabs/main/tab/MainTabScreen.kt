package com.china.psychy.android.feature.tabs.main.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.china.psychy.android.feature.tabs.main.list.MainListScreen
import com.china.psychy.android.feature.tabs.voddetail.VodDetailScreen

@Composable
fun MainTabScreen(
    component: MainTabComponent,
    modifier: Modifier
) {
    Children(
        stack = component.childStack,
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is MainTabComponent.Child.MainList -> MainListScreen(
                component = child.component,
                modifier = modifier
            )

            is MainTabComponent.Child.VodDetails -> VodDetailScreen(
                component = child.component,
                modifier = modifier
            )
        }
    }
}