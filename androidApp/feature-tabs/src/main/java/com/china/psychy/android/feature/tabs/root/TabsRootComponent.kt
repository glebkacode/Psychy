package com.china.psychy.android.feature.tabs.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.tabs.fims.FilmsTabComponent
import com.china.psychy.android.feature.tabs.main.tab.MainTabComponent
import com.china.psychy.android.feature.tabs.profile.ProfileTabComponent
import com.china.psychy.android.feature.tabs.serials.SerialsTabComponent
import com.china.psychy.android.feature.tabs.tv.TvTabComponent

interface TabsRootComponent {
    val childStack: Value<ChildStack<*, Child>>
    fun onMainTabSelected()
    fun onTvTabsSelected()
    fun onFilmsTabSelected()
    fun onSerialTabSelected()
    fun onProfileTabSelected()
    fun onPurchasedCompleted()
    sealed class Child {
        class MainChild(val component: MainTabComponent) : Child()
        class TvChild(val component: TvTabComponent) : Child()
        class FilmsChild(val component: FilmsTabComponent) : Child()
        class SerialChild(val component: SerialsTabComponent) : Child()
        class ProfileChild(val component: ProfileTabComponent) : Child()
    }
}