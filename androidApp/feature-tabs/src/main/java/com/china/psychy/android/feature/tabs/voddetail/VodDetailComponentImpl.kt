package com.china.psychy.android.feature.tabs.voddetail

import com.arkivanov.decompose.ComponentContext

class VodDetailComponentImpl(
    componentContext: ComponentContext,
    private val openPlayback: () -> Unit,
    private val openPurchase: () -> Unit
) : VodDetailComponent, ComponentContext by componentContext {
    override fun onPlayButtonClicked() {
        openPlayback()
    }

    override fun onBuyVodClicked() {

    }


}