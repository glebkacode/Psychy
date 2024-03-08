package com.china.psychy.android.feature.tabs.main.tab

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.tabs.main.list.MainListComponent
import com.china.psychy.android.feature.tabs.voddetail.VodDetailComponent

interface MainTabComponent {
    val childStack: Value<ChildStack<*, Child>>
    fun onItemSelected()
    sealed class Child {
        class MainList(val component: MainListComponent) : Child()
        class VodDetails(val component: VodDetailComponent) : Child()
    }
    sealed class Output {
        object OpenPlayback : Output()
    }
}