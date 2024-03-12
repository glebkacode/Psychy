package com.china.psychy.android.feature.tabs.voddetail

import com.china.psychy.android.feature.tabs.voddetail.VodDetailComponent.Model

internal val statesToModel: (VodDetailStore.State) -> Model =
    { state -> Model(isPurchasedCompleted = state.purchaseCompleted) }