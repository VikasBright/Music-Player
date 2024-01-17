

package com.example.mymusicplayer.preferences

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat.SRC_IN
import androidx.core.text.parseAsHtml
import androidx.fragment.app.DialogFragment
import code.name.monkey.appthemehelper.common.prefs.supportv7.ATEDialogPreference
import com.example.mymusicplayer.R
import com.example.mymusicplayer.extensions.accentTextColor
import com.example.mymusicplayer.extensions.colorButtons
import com.example.mymusicplayer.extensions.colorControlNormal
import com.example.mymusicplayer.extensions.materialDialog
import com.example.mymusicplayer.providers.BlacklistStore
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File

class BlacklistPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1,
    defStyleRes: Int = -1,
) : ATEDialogPreference(context, attrs, defStyleAttr, defStyleRes) {

    init {
        icon?.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                context.colorControlNormal(),
                SRC_IN
            )
    }
}

class BlacklistPreferenceDialog : DialogFragment() {
    companion object {
        fun newInstance(): BlacklistPreferenceDialog {
            return BlacklistPreferenceDialog()
        }
    }

    private lateinit var paths: ArrayList<String>

}
