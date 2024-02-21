package com.china.psychy.android.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.china.psychy.android.feature.auth.login.LoginScreen
import com.china.psychy.android.feature.lk.LkScreen

@Composable
fun RootScreen(component: RootComponent, modifier: Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.AuthChild -> LoginScreen(child.component, modifier)
            is RootComponent.Child.LkChild -> LkScreen(child.component)
        }
    }
}