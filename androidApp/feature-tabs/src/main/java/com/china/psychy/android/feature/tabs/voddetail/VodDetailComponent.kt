package com.china.psychy.android.feature.tabs.voddetail

import kotlinx.coroutines.flow.Flow

interface VodDetailComponent {
    val models: Flow<Model>
    fun onPlayButtonClicked()
    fun onBuyVodClicked()
    fun onPurchaseCompleted()

    data class Model(
        val isPurchasedCompleted: Boolean = false
    )
}