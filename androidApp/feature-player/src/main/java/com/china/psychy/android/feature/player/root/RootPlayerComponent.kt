package com.china.psychy.android.feature.player.root

import com.china.psychy.android.feature.player.controls.root.ControlsComponent
import com.china.psychy.android.feature.player.playback.PlaybackComponent

interface RootPlayerComponent {
    val playbackComponent: PlaybackComponent
    val controlsComponent: ControlsComponent
}