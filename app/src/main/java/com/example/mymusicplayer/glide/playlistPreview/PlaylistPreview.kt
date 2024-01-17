package com.example.mymusicplayer.glide.playlistPreview

import com.example.mymusicplayer.db.PlaylistEntity
import com.example.mymusicplayer.db.PlaylistWithSongs
import com.example.mymusicplayer.db.toSongs
import com.example.mymusicplayer.model.Song

class PlaylistPreview(val playlistWithSongs: PlaylistWithSongs) {

    val playlistEntity: PlaylistEntity get() = playlistWithSongs.playlistEntity
    val songs: List<Song> get() = playlistWithSongs.songs.toSongs()

    override fun equals(other: Any?): Boolean {
        println("Glide equals $this $other")
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlaylistPreview
        if (other.playlistEntity.playListId != playlistEntity.playListId) return false
        return other.songs.size == songs.size
    }

    override fun hashCode(): Int {
        var result = playlistEntity.playListId.hashCode()
        result = 31 * result + playlistWithSongs.songs.size
        println("Glide $result")
        return result
    }
}