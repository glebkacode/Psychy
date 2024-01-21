package com.china.psychy.android.login.shared

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun Password(
    text: String,
    onTextChanged: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChanged
    )
}