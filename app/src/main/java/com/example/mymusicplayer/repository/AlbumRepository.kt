

package com.example.mymusicplayer.repository

import android.provider.MediaStore.Audio.AudioColumns
import com.example.mymusicplayer.helper.SortOrder
import com.example.mymusicplayer.model.Album
import com.example.mymusicplayer.model.Song
import com.example.mymusicplayer.util.PreferenceUtil
import java.text.Collator


interface AlbumRepository {
    fun albums(): List<Album>

    fun albums(query: String): List<Album>

    fun album(albumId: Long): Album
}

class RealAlbumRepository(private val songRepository: RealSongRepository) :
    AlbumRepository {

    override fun albums(): List<Album> {
        val songs = songRepository.songs(
            songRepository.makeSongCursor(
                null,
                null,
                getSongLoaderSortOrder()
            )
        )
        return splitIntoAlbums(songs)
    }

    override fun albums(query: String): List<Album> {
        val songs = songRepository.songs(
            songRepository.makeSongCursor(
                AudioColumns.ALBUM + " LIKE ?",
                arrayOf("%$query%"),
                getSongLoaderSortOrder()
            )
        )
        return splitIntoAlbums(songs)
    }

    override fun album(albumId: Long): Album {
        val cursor = songRepository.makeSongCursor(
            AudioColumns.ALBUM_ID + "=?",
            arrayOf(albumId.toString()),
            getSongLoaderSortOrder()
        )
        val songs = songRepository.songs(cursor)
        val album = Album(albumId, songs)
        return sortAlbumSongs(album)
    }

    // We don't need sorted list of songs (with sortAlbumSongs())
    // cuz we are just displaying Albums(Cover Arts) anyway and not songs
    fun splitIntoAlbums(
        songs: List<Song>,
        sorted: Boolean = true
    ): List<Album> {
        val grouped = songs.groupBy { it.albumId }.map { Album(it.key, it.value) }
        if (!sorted) return grouped
        val collator = Collator.getInstance()
        return when (PreferenceUtil.albumSortOrder) {
            SortOrder.AlbumSortOrder.ALBUM_A_Z -> {
                grouped.sortedWith { a1, a2 -> collator.compare(a1.title, a2.title) }
            }
            SortOrder.AlbumSortOrder.ALBUM_Z_A -> {
                grouped.sortedWith { a1, a2 -> collator.compare(a2.title, a1.title) }
            }
            SortOrder.AlbumSortOrder.ALBUM_ARTIST -> {
                grouped.sortedWith { a1, a2 -> collator.compare(a1.albumArtist, a2.albumArtist) }
            }
            SortOrder.AlbumSortOrder.ALBUM_NUMBER_OF_SONGS -> {
                grouped.sortedByDescending { it.songCount }
            }
            else -> grouped
        }
    }

    private fun sortAlbumSongs(album: Album): Album {
        val collator = Collator.getInstance()
        val songs = when (PreferenceUtil.albumDetailSongSortOrder) {
            SortOrder.AlbumSongSortOrder.SONG_TRACK_LIST -> album.songs.sortedWith { o1, o2 ->
                o1.trackNumber.compareTo(o2.trackNumber)
            }
            SortOrder.AlbumSongSortOrder.SONG_A_Z -> {
                album.songs.sortedWith { o1, o2 -> collator.compare(o1.title, o2.title) }
            }
            SortOrder.AlbumSongSortOrder.SONG_Z_A -> {
                album.songs.sortedWith { o1, o2 -> collator.compare(o2.title, o1.title) }
            }
            SortOrder.AlbumSongSortOrder.SONG_DURATION -> album.songs.sortedWith { o1, o2 ->
                o1.duration.compareTo(o2.duration)
            }
            else -> throw IllegalArgumentException("invalid ${PreferenceUtil.albumDetailSongSortOrder}")
        }
        return album.copy(songs = songs)
    }

    private fun getSongLoaderSortOrder(): String {
        var albumSortOrder = PreferenceUtil.albumSortOrder
        if (albumSortOrder == SortOrder.AlbumSortOrder.ALBUM_NUMBER_OF_SONGS)
            albumSortOrder = SortOrder.AlbumSortOrder.ALBUM_A_Z
        return albumSortOrder + ", " +
                PreferenceUtil.albumSongSortOrder
    }
}
