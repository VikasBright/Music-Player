

package com.example.mymusicplayer.model

import androidx.annotation.StringRes
import com.example.mymusicplayer.HomeSection

data class Home(
    val arrayList: List<Any>,
    @HomeSection
    val homeSection: Int,
    @StringRes
    val titleRes: Int
)