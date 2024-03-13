package com.china.psychy.android.feature.tabs.purchase.successpay

import com.arkivanov.decompose.ComponentContext

class SuccessPayComponentImpl(
    componentContext: ComponentContext,
    private val onPurchasedCompleted: () -> Unit
) : ComponentContext by componentContext, SuccessPayComponent {
    override fun onConfirmClicked() {
        onPurchasedCompleted()
    }
}