package com.china.psychy.android.feature.tabs.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.china.psychy.android.feature.tabs.fims.FilmsTabScreen
import com.china.psychy.android.feature.tabs.main.tab.MainTabScreen
import com.china.psychy.android.feature.tabs.profile.ProfileTabScreen
import com.china.psychy.android.feature.tabs.serials.SerialsTabScreen
import com.china.psychy.android.feature.tabs.tv.TvTabScreen
import me.tatarka.inject.annotations.Inject

@Inject
@Composable
fun TabsRootScreen(
    component: TabsRootComponent,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabsItem(component = component, modifier = Modifier.weight(1f))
        BottomBar(component = component, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun TabsItem(
    component: TabsRootComponent,
    modifier: Modifier
) {
    Children(
        stack = component.childStack,
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is TabsRootComponent.Child.MainChild -> MainTabScreen(
                component = child.component,
                modifier = Modifier.fillMaxSize()
            )

            is TabsRootComponent.Child.TvChild -> TvTabScreen(
                component = child.component,
                modifier = Modifier.fillMaxSize()
            )

            is TabsRootComponent.Child.FilmsChild -> FilmsTabScreen(
                component = child.component,
                modifier = Modifier.fillMaxSize()
            )

            is TabsRootComponent.Child.SerialChild -> SerialsTabScreen(
                component = child.component,
                modifier = Modifier.fillMaxSize()
            )

            is TabsRootComponent.Child.ProfileChild -> ProfileTabScreen(
                component = child.component,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun BottomBar(
    component: TabsRootComponent,
    modifier: Modifier
) {
    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    NavigationBar(modifier) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Main") },
            selected = activeComponent is TabsRootComponent.Child.MainChild,
            onClick = { component.onMainTabSelected() },
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Tv") },
            selected = activeComponent is TabsRootComponent.Child.TvChild,
            onClick = { component.onTvTabsSelected() },
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Films") },
            selected = activeComponent is TabsRootComponent.Child.FilmsChild,
            onClick = { component.onFilmsTabSelected() },
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Serials") },
            selected = activeComponent is TabsRootComponent.Child.SerialChild,
            onClick = { component.onSerialTabSelected() },
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Profile") },
            selected = activeComponent is TabsRootComponent.Child.ProfileChild,
            onClick = { component.onProfileTabSelected() },
        )
    }
}