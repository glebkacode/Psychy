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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.china.psychy.android.feature.player.R

@Composable
fun MenuListUi(
    component: SideMenuListComponent,
    modifier: Modifier
) {
    Column(modifier) {
        PrimarySettings()
        SecondarySettingsItem(
            title = "Качество видео",
            onClick = { component.onVideoQualityClicked() }
        )
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
    IconButton(onClick = { onClick() }) {
        Icon(painter = painterResource(id = iconId), contentDescription = "Icon settings item")
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = title,
        fontSize = 12.sp
    )
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
            fontSize = 17.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Icon secondary settings item"
        )
    }
}