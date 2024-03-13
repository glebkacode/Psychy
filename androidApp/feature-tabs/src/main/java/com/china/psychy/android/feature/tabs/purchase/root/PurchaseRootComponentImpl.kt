package com.china.psychy.android.feature.tabs.purchase.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.tabs.purchase.choosepayment.ChoosePaymentComponentImpl
import com.china.psychy.android.feature.tabs.purchase.root.PurchaseRootComponent.Child
import com.china.psychy.android.feature.tabs.purchase.successpay.SuccessPayComponentImpl
import kotlinx.serialization.Serializable

class PurchaseRootComponentImpl(
    componentContext: ComponentContext,
    override val title: String,
    override val description: String,
    private val onDismissed: () -> Unit
) : ComponentContext by componentContext, PurchaseRootComponent {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.ConfirmPayment,
        childFactory = ::child
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    override fun onDismissClicked() {
        onDismissed()
    }

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when(config) {
            is Config.ConfirmPayment -> Child.ChoosePayment(
                ChoosePaymentComponentImpl(
                    componentContext = componentContext,
                    openSuccessPayment = { navigation.push(Config.SuccessPay) }
                )
            )
            Config.SuccessPay -> Child.SuccessPay(
                SuccessPayComponentImpl(
                    componentContext = componentContext,
                    onPurchasedCompleted = { onDismissed() }
                )
            )
        }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object ConfirmPayment : Config
        @Serializable
        data object SuccessPay : Config
    }
}