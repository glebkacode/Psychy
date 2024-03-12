package com.china.psychy.android.feature.purchase.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.purchase.choosepayment.ChoosePaymentComponentImpl
import com.china.psychy.android.feature.purchase.root.PurchaseRootComponent.Child
import com.china.psychy.android.feature.purchase.successpay.SuccessPayComponentImpl
import kotlinx.serialization.Serializable

class PurchaseRootComponentImpl(
    componentContext: ComponentContext,
    private val onPaymentCompleted: () -> Unit
) : ComponentContext by componentContext, PurchaseRootComponent {

    private val dialogNavigation = SlotNavigation<Config>()

    private val _dialogSlot =
        childSlot<Config, Child>(
            source = dialogNavigation,
            serializer = null,
            handleBackButton = true,
            childFactory = ::child
        )

    override val dialogSlot: Value<ChildSlot<*, Child>> = _dialogSlot

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when(config) {
            is Config.ConfirmPayment -> Child.ChoosePayment(
                ChoosePaymentComponentImpl(componentContext = componentContext)
            )
            Config.SuccessPay -> Child.SuccessPay(
                SuccessPayComponentImpl(componentContext = componentContext)
            )
        }

    override fun onPurchaseClicked() {
        onPaymentCompleted()
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object ConfirmPayment : Config
        @Serializable
        data object SuccessPay : Config
    }
}