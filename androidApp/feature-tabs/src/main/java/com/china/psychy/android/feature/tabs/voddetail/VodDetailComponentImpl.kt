package com.china.psychy.android.feature.tabs.voddetail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.china.psychy.android.feature.tabs.voddetail.VodDetailStore.Intent
import kotlinx.coroutines.flow.mapNotNull
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

    override fun onPlayButtonClicked() {
        openPlayback()
    }

    override fun onBuyVodClicked() {
        openPurchase()
    }

    override fun onPurchaseCompleted() {
        vodDetailStore.accept(Intent.PurchaseCompleted)
    }
}