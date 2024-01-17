package com.example.mymusicplayer.interfaces

import com.example.mymusicplayer.model.Album
import com.example.mymusicplayer.model.Artist
import com.example.mymusicplayer.model.Genre

interface IHomeClickListener {
    fun onAlbumClick(album: Album)

    fun onArtistClick(artist: Artist)

    fun onGenreClick(genre: Genre)
}