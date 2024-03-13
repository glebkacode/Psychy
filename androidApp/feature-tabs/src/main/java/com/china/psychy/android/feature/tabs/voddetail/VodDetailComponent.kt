package com.china.psychy.android.feature.tabs.voddetail

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.china.psychy.android.feature.tabs.purchase.root.PurchaseRootComponent
import kotlinx.coroutines.flow.Flow

interface VodDetailComponent {
    val models: Flow<Model>
    val dialogSlot: Value<ChildSlot<*, PurchaseRootComponent>>
    fun onPlayButtonClicked()
    fun onBuyVodClicked()
    fun onPurchaseCompleted()

    data class Model(
        val isPurchasedCompleted: Boolean = false
    )
}