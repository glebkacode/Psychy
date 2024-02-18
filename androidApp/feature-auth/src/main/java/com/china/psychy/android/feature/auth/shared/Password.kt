package com.china.psychy.android.feature.auth.shared

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Password(
    text: String,
    isValid: Boolean,
    onTextChanged: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChanged
    )
    if (!isValid) {
        Text(
            text = "Password is wrong",
            color = Color.Red
        )
    }
}