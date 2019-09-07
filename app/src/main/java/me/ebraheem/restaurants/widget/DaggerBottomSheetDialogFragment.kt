package me.ebraheem.restaurants.widget

import android.app.Dialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.DaggerAppCompatDialogFragment
import android.os.Bundle

/** BottomSheetDialogFragment implementation that supports injection with Dagger */
open class DaggerBottomSheetDialogFragment : DaggerAppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme)
    }
}