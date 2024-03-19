package com.china.psychy.android.feature.player.controls.mainplayback.sidemenu.menulist

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.china.psychy.android.feature.player.R

@Composable
fun MenuListUi(
    component: SideMenuListComponent,
    modifier: Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        PrimarySettings()
        Spacer(modifier = Modifier.height(16.dp))
        SecondarySettingsItem(
            title = "Качество видео",
            onClick = { component.onVideoQualityClicked() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        SecondarySettingsItem(
            title = "Аудио и субтитры",
            onClick = { component.onAudioSubtitleClicked() }
        )
    }
}

@Composable
fun PrimarySettings() {
    Row(
        modifier = Modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        PrimarySettingsItem(
            title = "Смотреть позже",
            iconId = R.drawable.ic_bookmark,
            onClick = { }
        )
        Spacer(modifier = Modifier.width(16.dp))
        PrimarySettingsItem(
            title = "Поделиться",
            iconId = R.drawable.ic_share,
            onClick = { }
        )
    }
}

@Composable
fun PrimarySettingsItem(
    title: String,
    @DrawableRes iconId: Int,
    onClick: () -> Unit
) {
    OutlinedButton(onClick = { onClick() }) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Icon settings item",
                modifier = Modifier
                    .height(16.dp)
                    .width(16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun SecondarySettingsItem(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Icon secondary settings item"
        )
    }
}

@Preview
@Composable
fun PrimarySettingsItemPreview() {
    MaterialTheme {
        PrimarySettingsItem(
            title = "Смотреть позже",
            iconId = R.drawable.ic_bookmark
        ) {}
    }
}

@Preview
@Composable
fun PrimarySettingsPreview() {
    MaterialTheme {
        PrimarySettings()
    }
}

@Preview
@Composable
fun MenuListUiPreview() {
    MaterialTheme {
        MenuListUi(
            component = SideMenuListComponentPreview(),
            modifier = Modifier
        )
    }
}