package com.app.shourtcuttask.presentation.explanation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.app.shourtcuttask.R
import com.app.shourtcuttask.databinding.ComicExplanationViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExplanationTray(private val uiModel: ExplanationTrayUIModel) : BottomSheetDialogFragment() {

    private lateinit var viewBinding: ComicExplanationViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = ComicExplanationViewBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        viewBinding.comicExplanationTitle.text = uiModel.title
        viewBinding.comicExplanationDescription.text = uiModel.description
    }

}

data class ExplanationTrayUIModel(val title: String, val description: String)