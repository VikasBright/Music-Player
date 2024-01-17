
package com.example.mymusicplayer.helper.menu

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.example.mymusicplayer.R
import com.example.mymusicplayer.helper.MusicPlayerRemote
import com.example.mymusicplayer.model.Song
import com.example.mymusicplayer.repository.RealRepository
import com.example.mymusicplayer.util.MusicUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object SongsMenuHelper : KoinComponent {
    fun handleMenuClick(
        activity: FragmentActivity,
        songs: List<Song>,
        menuItemId: Int
    ): Boolean {
        when (menuItemId) {
            R.id.action_play_next -> {
                MusicPlayerRemote.playNext(songs)
                return true
            }
            R.id.action_add_to_current_playing -> {
                MusicPlayerRemote.enqueue(songs)
                return true
            }
            R.id.action_add_to_playlist -> {

                return true
            }
            R.id.action_share -> {
                activity.startActivity(
                    Intent.createChooser(
                        MusicUtil.createShareMultipleSongIntent(activity, songs),
                        null
                    )
                )
                return true
            }
            R.id.action_delete_from_device -> {
                return true
            }
        }
        return false
    }
}
