package com.example.mymusicplayer.model.smartplaylist

import androidx.annotation.DrawableRes
import com.example.mymusicplayer.R
import com.example.mymusicplayer.model.AbsCustomPlaylist

abstract class AbsSmartPlaylist(
    name: String,
    @DrawableRes val iconRes: Int = R.drawable.ic_queue_music
) : AbsCustomPlaylist(
    id = PlaylistIdGenerator(name, iconRes),
    name = name
)