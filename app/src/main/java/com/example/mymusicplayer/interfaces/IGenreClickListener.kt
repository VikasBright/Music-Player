package com.example.mymusicplayer.interfaces

import android.view.View
import com.example.mymusicplayer.model.Genre

interface IGenreClickListener {
    fun onClickGenre(genre: Genre, view: View)
}