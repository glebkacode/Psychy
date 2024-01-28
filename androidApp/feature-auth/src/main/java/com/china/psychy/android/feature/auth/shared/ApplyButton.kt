package com.china.psychy.android.feature.auth.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ApplyButton(
    text: String,
    onButtonClicked: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        onClick = { onButtonClicked() }
    ) {
        Text(text = text)
    }
}