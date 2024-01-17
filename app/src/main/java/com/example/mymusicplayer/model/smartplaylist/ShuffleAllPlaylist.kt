package com.example.mymusicplayer.model.smartplaylist

import com.example.mymusicplayer.App
import com.example.mymusicplayer.R
import com.example.mymusicplayer.model.Song
import kotlinx.parcelize.Parcelize

@Parcelize
class ShuffleAllPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.action_shuffle_all),
    iconRes = R.drawable.ic_shuffle
) {
    override fun songs(): List<Song> {
        return songRepository.songs()
    }
}