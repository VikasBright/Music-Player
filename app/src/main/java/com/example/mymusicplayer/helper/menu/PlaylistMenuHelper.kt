
package com.example.mymusicplayer.helper.menu

import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import com.example.mymusicplayer.R
import com.example.mymusicplayer.db.PlaylistWithSongs
import com.example.mymusicplayer.db.toSongs
import com.example.mymusicplayer.helper.MusicPlayerRemote
import com.example.mymusicplayer.repository.RealRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object PlaylistMenuHelper : KoinComponent {

    fun handleMenuClick(
        activity: FragmentActivity,
        playlistWithSongs: PlaylistWithSongs,
        item: MenuItem
    ): Boolean {
        when (item.itemId) {
            R.id.action_play -> {
                MusicPlayerRemote.openQueue(playlistWithSongs.songs.toSongs(), 0, true)
                return true
            }
            R.id.action_play_next -> {
                MusicPlayerRemote.playNext(playlistWithSongs.songs.toSongs())
                return true
            }
            R.id.action_add_to_playlist -> {
                return true
            }
            R.id.action_add_to_current_playing -> {
                MusicPlayerRemote.enqueue(playlistWithSongs.songs.toSongs())
                return true
            }
            R.id.action_rename_playlist -> {

                return true
            }
            R.id.action_delete_playlist -> {

                return true
            }
            R.id.action_save_playlist -> {
                return true
            }
        }
        return false
    }
}
