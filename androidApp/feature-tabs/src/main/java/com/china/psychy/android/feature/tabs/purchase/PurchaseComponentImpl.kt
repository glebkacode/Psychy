package com.china.psychy.android.feature.tabs.purchase

class PurchaseComponentImpl(
    override val title: String,
    override val description: String,
    private val onDismissed: () -> Unit
) : PurchaseComponent {

    override fun onDismissClicked() {
        onDismissed()
    }
}