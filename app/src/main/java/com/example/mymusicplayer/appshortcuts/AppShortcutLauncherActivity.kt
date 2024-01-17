
package com.example.mymusicplayer.appshortcuts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.mymusicplayer.appshortcuts.shortcuttype.LastAddedShortcutType
import com.example.mymusicplayer.appshortcuts.shortcuttype.ShuffleAllShortcutType
import com.example.mymusicplayer.appshortcuts.shortcuttype.TopTracksShortcutType
import com.example.mymusicplayer.extensions.extraNotNull
import com.example.mymusicplayer.model.Playlist
import com.example.mymusicplayer.model.smartplaylist.LastAddedPlaylist
import com.example.mymusicplayer.model.smartplaylist.ShuffleAllPlaylist
import com.example.mymusicplayer.model.smartplaylist.TopTracksPlaylist
import com.example.mymusicplayer.service.MusicService
import com.example.mymusicplayer.service.MusicService.Companion.ACTION_PLAY_PLAYLIST
import com.example.mymusicplayer.service.MusicService.Companion.INTENT_EXTRA_PLAYLIST
import com.example.mymusicplayer.service.MusicService.Companion.INTENT_EXTRA_SHUFFLE_MODE
import com.example.mymusicplayer.service.MusicService.Companion.SHUFFLE_MODE_NONE
import com.example.mymusicplayer.service.MusicService.Companion.SHUFFLE_MODE_SHUFFLE

class AppShortcutLauncherActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (extraNotNull(KEY_SHORTCUT_TYPE, SHORTCUT_TYPE_NONE).value) {
            SHORTCUT_TYPE_SHUFFLE_ALL -> {
                startServiceWithPlaylist(
                    SHUFFLE_MODE_SHUFFLE, ShuffleAllPlaylist()
                )
                DynamicShortcutManager.reportShortcutUsed(this, ShuffleAllShortcutType.id)
            }
            SHORTCUT_TYPE_TOP_TRACKS -> {
                startServiceWithPlaylist(
                    SHUFFLE_MODE_NONE, TopTracksPlaylist()
                )
                DynamicShortcutManager.reportShortcutUsed(this, TopTracksShortcutType.id)
            }
            SHORTCUT_TYPE_LAST_ADDED -> {
                startServiceWithPlaylist(
                    SHUFFLE_MODE_NONE, LastAddedPlaylist()
                )
                DynamicShortcutManager.reportShortcutUsed(this, LastAddedShortcutType.id)
            }
        }
        finish()
    }

    private fun startServiceWithPlaylist(shuffleMode: Int, playlist: Playlist) {
        val intent = Intent(this, MusicService::class.java)
        intent.action = ACTION_PLAY_PLAYLIST

        val bundle = bundleOf(
            INTENT_EXTRA_PLAYLIST to playlist,
            INTENT_EXTRA_SHUFFLE_MODE to shuffleMode
        )

        intent.putExtras(bundle)

        startService(intent)
    }

    companion object {
        const val KEY_SHORTCUT_TYPE = "com.example.mymusicplayer.appshortcuts.ShortcutType"
        const val SHORTCUT_TYPE_SHUFFLE_ALL = 0L
        const val SHORTCUT_TYPE_TOP_TRACKS = 1L
        const val SHORTCUT_TYPE_LAST_ADDED = 2L
        const val SHORTCUT_TYPE_NONE = 4L
    }
}
