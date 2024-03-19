package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.videoquality

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VideoQualityUi(
    component: VideoQualityComponent
) {
    Column {
        VideoQualityHeader()
        Spacer(modifier = Modifier.height(16.dp))
        VideoQualityItem(
            text = "Авто",
            subText = "(240p)",
            onClick = { component.onQualitySelected(quality = VideoQuality.Auto) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        VideoQualityItem(
            text = "Низкое",
            subText = "(360p)",
            onClick = { component.onQualitySelected(quality = VideoQuality.Low) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        VideoQualityItem(
            text = "Среднее",
            subText = "(520p)",
            onClick = { component.onQualitySelected(quality = VideoQuality.Medium) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        VideoQualityItem(
            text = "Высокое",
            subText = "(720p)",
            onClick = { component.onQualitySelected(quality = VideoQuality.High) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        VideoQualityItem(
            text = "Сверхвысокое",
            subText = "(1080p)",
            onClick = { component.onQualitySelected(quality = VideoQuality.ExtraHigh) }
        )
    }
}

@Composable
fun VideoQualityHeader() {
    Text(
        text = "Качество видео",
        fontSize = 24.sp
    )
}

@Composable
fun VideoQualityItem(
    text: String,
    subText: String,
    onClick: () -> Unit
) {
    Row(modifier = Modifier.clickable { onClick() }) {
        Text(
            text = text,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = subText,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun VideoQualityItemPreview() {
    MaterialTheme {
        VideoQualityItem(
            text = "Сверхвысокое",
            subText = "(1080p)",
            onClick = {  }
        )
    }
}

@Preview
@Composable
fun VideoQualityUiPreview() {
    MaterialTheme {
        VideoQualityUi(
            component = VideoQualityComponentPreview()
        )
    }
}