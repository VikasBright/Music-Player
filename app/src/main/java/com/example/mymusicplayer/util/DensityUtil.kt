
package com.example.mymusicplayer.util

import android.content.Context
import android.util.TypedValue


object DensityUtil {
    fun getScreenHeight(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels
    }

    fun getScreenWidth(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }

    private fun toDP(context: Context, value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(), context.resources.displayMetrics
        ).toInt()
    }

    @JvmStatic
    fun dip2px(context: Context, dpVale: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpVale * scale + 0.5f).toInt()
    }
}