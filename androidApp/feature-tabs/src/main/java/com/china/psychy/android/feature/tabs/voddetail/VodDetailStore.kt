package com.china.psychy.android.feature.tabs.voddetail

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.china.psychy.android.feature.tabs.voddetail.VodDetailStore.Intent
import com.china.psychy.android.feature.tabs.voddetail.VodDetailStore.State

interface VodDetailStore : Store<Intent, State, Nothing> {

    sealed class Intent : JvmSerializable {
        data object PurchaseCompleted : Intent()
    }

    data class State(
        val purchaseCompleted: Boolean = false
    ) : JvmSerializable
}