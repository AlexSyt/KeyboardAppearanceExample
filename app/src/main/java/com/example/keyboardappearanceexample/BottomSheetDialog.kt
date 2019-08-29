package com.example.keyboardappearanceexample

import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetDialog(
    context: Context,
    private val enableAnimation: Boolean,
    private val useCustomPadding: Boolean
) : BottomSheetDialog(context, R.style.Theme_MaterialComponents_Light_BottomSheetDialog) {

    init {
        setContentView(R.layout.dialog_bottom_sheet)
    }

    override fun onStart() {
        super.onStart()
        val bottomSheet = findViewById<View>(R.id.design_bottom_sheet)
        BottomSheetBehavior.from(bottomSheet).apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
        }
        window?.apply {
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            if (enableAnimation) {
                getAnimator(this).start()
            }
        }
    }

    private fun getAnimator(window: Window): BaseKeyboardAnimator =
        if (useCustomPadding) {
            CustomizableKeyboardAnimator(window)
        } else {
            SimpleKeyboardAnimator(window)
        }
}