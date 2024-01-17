
package com.example.mymusicplayer.appwidgets

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import code.name.monkey.appthemehelper.util.VersionUtils
import com.example.mymusicplayer.R
import com.example.mymusicplayer.activities.MainActivity
import com.example.mymusicplayer.appwidgets.base.BaseAppWidget
import com.example.mymusicplayer.extensions.getTintedDrawable
import com.example.mymusicplayer.service.MusicService
import com.example.mymusicplayer.service.MusicService.Companion.ACTION_REWIND
import com.example.mymusicplayer.service.MusicService.Companion.ACTION_SKIP
import com.example.mymusicplayer.service.MusicService.Companion.ACTION_TOGGLE_PAUSE
import com.example.mymusicplayer.util.PreferenceUtil

class AppWidgetText : BaseAppWidget() {
    override fun defaultAppWidget(context: Context, appWidgetIds: IntArray) {
        val appWidgetView = RemoteViews(context.packageName, R.layout.app_widget_text)

        appWidgetView.setImageViewBitmap(
            R.id.button_next,
            context.getTintedDrawable(R.drawable.ic_skip_next, ContextCompat.getColor(
                context, code.name.monkey.appthemehelper.R.color.md_white_1000
            )).toBitmap()
        )
        appWidgetView.setImageViewBitmap(
            R.id.button_prev,
            context.getTintedDrawable(R.drawable.ic_skip_previous, ContextCompat.getColor(
                context, code.name.monkey.appthemehelper.R.color.md_white_1000
            )
            ).toBitmap()
        )
        appWidgetView.setImageViewBitmap(
            R.id.button_toggle_play_pause,
            context.getTintedDrawable(R.drawable.ic_play_arrow_white_32dp, ContextCompat.getColor(
                context, code.name.monkey.appthemehelper.R.color.md_white_1000
            )
            ).toBitmap()
        )

        appWidgetView.setTextColor(
            R.id.title, ContextCompat.getColor(context, code.name.monkey.appthemehelper.R.color.md_white_1000)
        )
        appWidgetView.setTextColor(
            R.id.text, ContextCompat.getColor(context, code.name.monkey.appthemehelper.R.color.md_white_1000)
        )

        linkButtons(context, appWidgetView)
        pushUpdate(context, appWidgetIds, appWidgetView)
    }

    /**
     * Link up various button actions using [PendingIntent].
     */
    private fun linkButtons(context: Context, views: RemoteViews) {
        val action = Intent(context, MainActivity::class.java)
            .putExtra(
                MainActivity.EXPAND_PANEL,
                PreferenceUtil.isExpandPanel
            )

        val serviceName = ComponentName(context, MusicService::class.java)

        // Home
        action.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        var pendingIntent = PendingIntent.getActivity(
            context, 0, action, if (VersionUtils.hasMarshmallow())
                PendingIntent.FLAG_IMMUTABLE
            else 0
        )
        views.setOnClickPendingIntent(R.id.image, pendingIntent)
        views.setOnClickPendingIntent(R.id.media_titles, pendingIntent)

        // Previous track
        pendingIntent = buildPendingIntent(context, ACTION_REWIND, serviceName)
        views.setOnClickPendingIntent(R.id.button_prev, pendingIntent)

        // Play and pause
        pendingIntent = buildPendingIntent(context, ACTION_TOGGLE_PAUSE, serviceName)
        views.setOnClickPendingIntent(R.id.button_toggle_play_pause, pendingIntent)

        // Next track
        pendingIntent = buildPendingIntent(context, ACTION_SKIP, serviceName)
        views.setOnClickPendingIntent(R.id.button_next, pendingIntent)
    }

    override fun performUpdate(service: MusicService, appWidgetIds: IntArray?) {
        val appWidgetView = RemoteViews(service.packageName, R.layout.app_widget_text)

        val isPlaying = service.isPlaying
        val song = service.currentSong

        // Set the titles and artwork
        if (song.title.isEmpty() && song.artistName.isEmpty()) {
            appWidgetView.setViewVisibility(R.id.media_titles, View.INVISIBLE)
        } else {
            appWidgetView.setViewVisibility(R.id.media_titles, View.VISIBLE)
            appWidgetView.setTextViewText(R.id.title, song.title)
            appWidgetView.setTextViewText(R.id.text, song.artistName)
        }
        // Link actions buttons to intents
        linkButtons(service, appWidgetView)

        // Set correct drawable for pause state
        val playPauseRes = if (isPlaying) R.drawable.ic_pause
        else R.drawable.ic_play_arrow_white_32dp
        appWidgetView.setImageViewBitmap(
            R.id.button_toggle_play_pause,
            service.getTintedDrawable(playPauseRes, ContextCompat.getColor(
                service, code.name.monkey.appthemehelper.R.color.md_white_1000)
            ).toBitmap()
        )
        appWidgetView.setImageViewBitmap(
            R.id.button_next,
            service.getTintedDrawable(
                R.drawable.ic_skip_next,
                ContextCompat.getColor(
                    service,
                    code.name.monkey.appthemehelper.R.color.md_white_1000
                )
            ).toBitmap()
        )
        appWidgetView.setImageViewBitmap(
            R.id.button_prev,
            service.getTintedDrawable(
                R.drawable.ic_skip_previous,
                ContextCompat.getColor(
                    service, code.name.monkey.appthemehelper.R.color.md_white_1000
                )
            ).toBitmap()
        )

        pushUpdate(service.applicationContext, appWidgetIds, appWidgetView)
    }

    companion object {

        const val NAME: String = "app_widget_text"

        private var mInstance: AppWidgetText? = null

        val instance: AppWidgetText
            @Synchronized get() {
                if (mInstance == null) {
                    mInstance = AppWidgetText()
                }
                return mInstance!!
            }
    }
}