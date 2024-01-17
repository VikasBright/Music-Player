package com.example.mymusicplayer.util.theme

import android.content.Context
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import com.example.mymusicplayer.R
import com.example.mymusicplayer.extensions.generalThemeValue
import com.example.mymusicplayer.util.PreferenceUtil
import com.example.mymusicplayer.util.theme.ThemeMode.*

@StyleRes
fun Context.getThemeResValue(): Int =
    if (PreferenceUtil.materialYou) {
        if (generalThemeValue == BLACK) R.style.Theme_RetroMusic_MD3_Black
        else R.style.Theme_MusicPlayer_MD3
    } else {
        when (generalThemeValue) {
            LIGHT -> R.style.Theme_MusicPlayer_Light
            DARK -> R.style.Theme_MusicPlayer_Base
            BLACK -> R.style.Theme_MusicPlayer_Black
            AUTO -> R.style.Theme_MusicPlayer_FollowSystem
        }
    }

fun Context.getNightMode(): Int = when (generalThemeValue) {
    LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
    DARK -> AppCompatDelegate.MODE_NIGHT_YES
    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
}