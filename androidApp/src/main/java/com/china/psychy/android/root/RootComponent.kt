package com.china.psychy.android.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.auth.login.AuthComponent
import com.china.psychy.android.feature.lk.LkComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class AuthChild(val component: AuthComponent) : Child()
        class LkChild(val component: LkComponent) : Child()
    }
}