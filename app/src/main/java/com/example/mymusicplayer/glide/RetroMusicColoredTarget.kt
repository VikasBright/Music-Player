
package com.example.mymusicplayer.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.example.mymusicplayer.App
import com.example.mymusicplayer.extensions.colorControlNormal
import com.example.mymusicplayer.glide.palette.BitmapPaletteTarget
import com.example.mymusicplayer.glide.palette.BitmapPaletteWrapper
import com.example.mymusicplayer.util.color.MediaNotificationProcessor
import com.bumptech.glide.request.transition.Transition

abstract class RetroMusicColoredTarget(view: ImageView) : BitmapPaletteTarget(view) {

    protected val defaultFooterColor: Int
        get() = getView().context.colorControlNormal()

    abstract fun onColorReady(colors: MediaNotificationProcessor)

    override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
        onColorReady(MediaNotificationProcessor.errorColor(App.getContext()))
    }

    override fun onResourceReady(
        resource: BitmapPaletteWrapper,
        transition: Transition<in BitmapPaletteWrapper>?
    ) {
        super.onResourceReady(resource, transition)
        MediaNotificationProcessor(App.getContext()).getPaletteAsync({
            onColorReady(it)
        }, resource.bitmap)
    }
}
