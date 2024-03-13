package com.china.psychy.android.feature.tabs.purchase.choosepayment

import com.arkivanov.decompose.ComponentContext

class ChoosePaymentComponentImpl(
    componentContext: ComponentContext,
    private val openSuccessPayment: () -> Unit
) : ChoosePaymentComponent, ComponentContext by componentContext {
    override fun onPayClicked() {
        openSuccessPayment()
    }
}