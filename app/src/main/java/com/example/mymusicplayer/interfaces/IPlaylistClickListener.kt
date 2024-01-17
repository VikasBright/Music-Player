package com.example.mymusicplayer.interfaces

import android.view.View
import com.example.mymusicplayer.db.PlaylistWithSongs

interface IPlaylistClickListener {
    fun onPlaylistClick(playlistWithSongs: PlaylistWithSongs, view: View)
}