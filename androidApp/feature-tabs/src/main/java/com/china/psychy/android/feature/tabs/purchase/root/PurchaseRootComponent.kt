package com.china.psychy.android.feature.tabs.purchase.root

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.tabs.purchase.choosepayment.ChoosePaymentComponent
import com.china.psychy.android.feature.tabs.purchase.successpay.SuccessPayComponent

interface PurchaseRootComponent {
    val title: String
    val description: String
    val childStack: Value<ChildStack<*, Child>>

    fun onDismissClicked()

    sealed class Child {
        class ChoosePayment(val component: ChoosePaymentComponent) : Child()
        class SuccessPay(val component: SuccessPayComponent) : Child()
    }
}