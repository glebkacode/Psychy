package com.china.psychy.android.feature.tabs.voddetail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.china.psychy.android.feature.tabs.purchase.PurchaseComponent
import com.china.psychy.android.feature.tabs.purchase.PurchaseComponentImpl
import com.china.psychy.android.feature.tabs.voddetail.VodDetailStore.Intent
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

class VodDetailComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    mainContext: CoroutineContext,
    ioContext: CoroutineContext,
    private val openPlayback: () -> Unit,
    private val openPurchase: () -> Unit
) : VodDetailComponent, ComponentContext by componentContext {

    private val vodDetailStore = VodDetailStoreFactory(
        storeFactory,
        mainContext,
        ioContext
    ).create()

    override val models = vodDetailStore.states.mapNotNull { state -> statesToModel.invoke(state) }

    private val dialogNavigation = SlotNavigation<PurchaseConfig>()

    private val _dialogSlot =
        childSlot<PurchaseConfig, PurchaseComponent>(
            source = dialogNavigation,
            serializer = null,
            handleBackButton = true,
            childFactory = { config, _ ->
                PurchaseComponentImpl(
                    title = config.title,
                    description = config.description,
                    onDismissed = dialogNavigation::dismiss,
                )
            }
        )

    override val dialogSlot: Value<ChildSlot<*, PurchaseComponent>> = _dialogSlot

    override fun onPlayButtonClicked() {
        openPlayback()
    }

    override fun onBuyVodClicked() {
        dialogNavigation.activate(
            PurchaseConfig(
                title = "Способ оплаты",
                description = "Выберите способ оплат"
            )
        )
        // openPurchase()
    }

    override fun onPurchaseCompleted() {
        vodDetailStore.accept(Intent.PurchaseCompleted)
    }

    @Serializable
    private data class PurchaseConfig(
        val title: String,
        val description: String
    )
}