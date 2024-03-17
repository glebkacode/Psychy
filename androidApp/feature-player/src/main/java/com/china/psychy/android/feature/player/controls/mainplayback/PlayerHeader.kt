package com.china.psychy.android.feature.player.controls.mainplayback

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.china.psychy.android.feature.player.R

@Composable
fun PlayerHeader(
    title: String,
    modifier: Modifier,
    onSettingsClicked: () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        IconButton(
            onClick = { },
            modifier = Modifier
                .height(32.dp)
                .width(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back button"
            )
        }
        Text(
            text = title,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_settings),
            contentDescription = "Icon settings",
            modifier = Modifier.clickable { onSettingsClicked() }
        )
    }
}

@Preview
@Composable
fun PlayerHeaderPreview() {
    MaterialTheme {
        PlayerHeader(
            title = "Джентельмены",
            modifier = Modifier,
            onSettingsClicked = {}
        )
    }
}