package com.china.psychy.android.feature.tabs.voddetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.china.psychy.android.feature.tabs.purchase.root.PurchaseRootScreen
import com.china.psychy.android.feature.tabs.voddetail.VodDetailComponent.Model

@Composable
fun VodDetailScreen(
    component: VodDetailComponent,
    modifier: Modifier
) {
    val models by component.models.collectAsState(initial = Model())

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { component.onPlayButtonClicked() }) {
            Text("VodDetail Screen")
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { component.onBuyVodClicked() }) {
            Text(text = "Buy the film")
        }

        if (models.isPurchasedCompleted) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Purchase feature return result")
        }
    }

    val dialogSlot by component.dialogSlot.subscribeAsState()
    dialogSlot.child?.instance?.also {
        PurchaseRootScreen(component = it, modifier = modifier)
    }
}