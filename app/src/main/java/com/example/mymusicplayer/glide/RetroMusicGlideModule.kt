
package com.example.mymusicplayer.glide

import android.content.Context
import android.graphics.Bitmap
import com.example.mymusicplayer.glide.artistimage.ArtistImage
import com.example.mymusicplayer.glide.artistimage.Factory
import com.example.mymusicplayer.glide.audiocover.AudioFileCover
import com.example.mymusicplayer.glide.audiocover.AudioFileCoverLoader
import com.example.mymusicplayer.glide.palette.BitmapPaletteTranscoder
import com.example.mymusicplayer.glide.palette.BitmapPaletteWrapper
import com.example.mymusicplayer.glide.playlistPreview.PlaylistPreview
import com.example.mymusicplayer.glide.playlistPreview.PlaylistPreviewLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

@GlideModule
class RetroMusicGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.prepend(
            PlaylistPreview::class.java,
            Bitmap::class.java,
            PlaylistPreviewLoader.Factory(context)
        )
        registry.prepend(
            AudioFileCover::class.java,
            InputStream::class.java,
            AudioFileCoverLoader.Factory()
        )
        registry.prepend(ArtistImage::class.java, InputStream::class.java, Factory(context))
        registry.register(
            Bitmap::class.java,
            BitmapPaletteWrapper::class.java, BitmapPaletteTranscoder()
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}