package com.example.mymusicplayer.model.smartplaylist

import com.example.mymusicplayer.App
import com.example.mymusicplayer.R
import com.example.mymusicplayer.model.Song
import kotlinx.parcelize.Parcelize

@Parcelize
class NotPlayedPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.not_recently_played),
    iconRes = R.drawable.ic_audiotrack
) {
    override fun songs(): List<Song> {
        return topPlayedRepository.notRecentlyPlayedTracks()
    }
}