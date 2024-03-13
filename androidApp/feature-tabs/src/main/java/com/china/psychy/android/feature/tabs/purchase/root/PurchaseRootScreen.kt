package com.china.psychy.android.feature.tabs.purchase.root

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.china.psychy.android.feature.tabs.purchase.choosepayment.ChoosePaymentScreen
import com.china.psychy.android.feature.tabs.purchase.successpay.SuccessPayScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseRootScreen(
    component: PurchaseRootComponent,
    modifier: Modifier
) {
/*    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Purchase feature",
            modifier = Modifier.clickable { component.onPurchaseClicked() }
        )
    }*/

    ModalBottomSheet(
        onDismissRequest = { component.onDismissClicked() },
        content = {
            Children(
                stack = component.childStack,
                modifier = modifier
            ) {
                when (val child = it.instance) {
                    is PurchaseRootComponent.Child.ChoosePayment -> ChoosePaymentScreen(component = child.component)
                    is PurchaseRootComponent.Child.SuccessPay -> SuccessPayScreen(component = child.component)
                }
            }
        }
    )
}