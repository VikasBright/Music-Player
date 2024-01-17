
package com.example.mymusicplayer.fragments.player.tiny

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import code.name.monkey.appthemehelper.util.ColorUtil
import com.example.mymusicplayer.R
import com.example.mymusicplayer.databinding.FragmentTinyControlsFragmentBinding
import com.example.mymusicplayer.fragments.base.AbsPlayerControlsFragment
import com.example.mymusicplayer.util.color.MediaNotificationProcessor

class TinyPlaybackControlsFragment :
    AbsPlayerControlsFragment(R.layout.fragment_tiny_controls_fragment) {
    private var _binding: FragmentTinyControlsFragmentBinding? = null
    private val binding get() = _binding!!

    override val shuffleButton: ImageButton
        get() = binding.shuffleButton

    override val repeatButton: ImageButton
        get() = binding.repeatButton

    override fun show() {}

    override fun hide() {}

    override fun setColor(color: MediaNotificationProcessor) {
        lastPlaybackControlsColor = color.secondaryTextColor
        lastDisabledPlaybackControlsColor = ColorUtil.withAlpha(color.secondaryTextColor, 0.25f)

        updateRepeatState()
        updateShuffleState()
    }

    override fun onUpdateProgressViews(progress: Int, total: Int) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTinyControlsFragmentBinding.bind(view)
    }

    override fun onServiceConnected() {
        updateRepeatState()
        updateShuffleState()
    }

    override fun onRepeatModeChanged() {
        updateRepeatState()
    }

    override fun onShuffleModeChanged() {
        updateShuffleState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
