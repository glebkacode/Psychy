package com.china.psychy.android.feature.tabs.purchase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseBottomSheet(
    component: PurchaseComponent
) {
    ModalBottomSheet(
        onDismissRequest = { component.onDismissClicked() },
        content = {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = component.title)
                Spacer(modifier = Modifier.height(64.dp))

                Text(text = component.description)
                Spacer(modifier = Modifier.height(64.dp))

                TextButton(
                    onClick = {
                        component.onDismissClicked()
                    }
                ) {
                    Text("Dismiss")
                }
                Spacer(modifier = Modifier.height(64.dp))
            }

        }
    )
}