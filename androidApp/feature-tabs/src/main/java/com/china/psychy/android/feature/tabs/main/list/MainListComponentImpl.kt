package com.china.psychy.android.feature.tabs.main.list

import com.arkivanov.decompose.ComponentContext

class MainListComponentImpl(
    componentContext: ComponentContext,
    private val openVodDetails: () -> Unit
) : MainListComponent, ComponentContext by componentContext {
    override fun onPosterClicked() {
        openVodDetails()
    }
}