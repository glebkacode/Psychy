package com.china.psychy.android.feature.purchase.root

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.purchase.choosepayment.ChoosePaymentComponent
import com.china.psychy.android.feature.purchase.successpay.SuccessPayComponent

interface PurchaseRootComponent {
    val dialogSlot: Value<ChildSlot<*, Child>>
    fun onPurchaseClicked()

    sealed class Child {
        class ChoosePayment(val component: ChoosePaymentComponent) : Child()
        class SuccessPay(val component: SuccessPayComponent) : Child()
    }
}