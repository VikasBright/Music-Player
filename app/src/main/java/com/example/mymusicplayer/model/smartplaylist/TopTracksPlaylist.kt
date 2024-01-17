package com.example.mymusicplayer.model.smartplaylist

import com.example.mymusicplayer.App
import com.example.mymusicplayer.R
import com.example.mymusicplayer.model.Song
import kotlinx.parcelize.Parcelize

@Parcelize
class TopTracksPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.my_top_tracks),
    iconRes = R.drawable.ic_trending_up
) {
    override fun songs(): List<Song> {
        return topPlayedRepository.topTracks()
    }
}