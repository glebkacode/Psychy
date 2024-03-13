package com.china.psychy.android.feature.tabs.purchase.choosepayment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChoosePaymentScreen(
    component: ChoosePaymentComponent
) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(text = "Choose payment title")
        Spacer(modifier = Modifier.height(64.dp))

        Text(text = "Choose payment description")
        Spacer(modifier = Modifier.height(64.dp))

        TextButton(
            onClick = {
                component.onPayClicked()
            }
        ) {
            Text("Pay")
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}