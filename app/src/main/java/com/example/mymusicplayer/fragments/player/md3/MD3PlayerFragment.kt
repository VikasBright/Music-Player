
package com.example.mymusicplayer.fragments.player.md3

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import code.name.monkey.appthemehelper.util.ATHUtil
import code.name.monkey.appthemehelper.util.ToolbarContentTintHelper
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.FragmentMd3PlayerBinding
import com.example.mymusicplayer.extensions.drawAboveSystemBars
import com.example.mymusicplayer.fragments.base.AbsPlayerFragment
import com.example.mymusicplayer.fragments.player.PlayerAlbumCoverFragment
import com.example.mymusicplayer.helper.MusicPlayerRemote
import com.example.mymusicplayer.model.Song
import com.example.mymusicplayer.util.color.MediaNotificationProcessor

class MD3PlayerFragment : AbsPlayerFragment(R.layout.fragment_md3_player) {

    private var lastColor: Int = 0
    override val paletteColor: Int
        get() = lastColor

    private lateinit var controlsFragment: MD3PlaybackControlsFragment

    private var _binding: FragmentMd3PlayerBinding? = null
    private val binding get() = _binding!!

    override fun onShow() {
        controlsFragment.show()
    }

    override fun onHide() {
        controlsFragment.hide()
    }

    override fun toolbarIconColor(): Int {
        return ATHUtil.resolveColor(requireContext(), androidx.appcompat.R.attr.colorControlNormal)
    }

    override fun onColorChanged(color: MediaNotificationProcessor) {
        controlsFragment.setColor(color)
        lastColor = color.backgroundColor
        libraryViewModel.updateColor(color.backgroundColor)

        ToolbarContentTintHelper.colorizeToolbar(
            binding.playerToolbar,
            ATHUtil.resolveColor(requireContext(), androidx.appcompat.R.attr.colorControlNormal),
            requireActivity()
        )
    }

    override fun toggleFavorite(song: Song) {
        super.toggleFavorite(song)
        if (song.id == MusicPlayerRemote.currentSong.id) {
            updateIsFavorite()
        }
    }

    override fun onFavoriteToggled() {
        toggleFavorite(MusicPlayerRemote.currentSong)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMd3PlayerBinding.bind(view)
        setUpSubFragments()
        setUpPlayerToolbar()
        playerToolbar().drawAboveSystemBars()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpSubFragments() {
        controlsFragment =
            childFragmentManager.findFragmentById(R.id.playbackControlsFragment) as MD3PlaybackControlsFragment
        val playerAlbumCoverFragment =
            childFragmentManager.findFragmentById(R.id.playerAlbumCoverFragment) as PlayerAlbumCoverFragment
        playerAlbumCoverFragment.setCallbacks(this)
    }

    private fun setUpPlayerToolbar() {

    }

    override fun onPlayingMetaChanged() {
        updateIsFavorite()
    }

    override fun playerToolbar(): Toolbar {
        return binding.playerToolbar
    }

    companion object {

        fun newInstance(): MD3PlayerFragment {
            return MD3PlayerFragment()
        }
    }
}
