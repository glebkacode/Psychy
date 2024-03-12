package com.china.psychy.android.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.auth.login.AuthComponent
import com.china.psychy.android.feature.player.PlayerComponent
import com.china.psychy.android.feature.purchase.root.PurchaseRootComponent
import com.china.psychy.android.feature.tabs.root.TabsRootComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class AuthChild(val component: AuthComponent) : Child()
        class TabsRootChild(val component: TabsRootComponent) : Child()
        class PurchaseChild(val component: PurchaseRootComponent) : Child()
        class PlayerChild(val component: PlayerComponent) : Child()
    }
}