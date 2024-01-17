package com.example.mymusicplayer.extensions

import androidx.core.view.WindowInsetsCompat
import com.example.mymusicplayer.util.PreferenceUtil
import com.example.mymusicplayer.util.RetroUtil

fun WindowInsetsCompat?.getBottomInsets(): Int {
    return if (PreferenceUtil.isFullScreenMode) {
        return 0
    } else {
        this?.getInsets(WindowInsetsCompat.Type.systemBars())?.bottom ?: RetroUtil.navigationBarHeight
    }
}
