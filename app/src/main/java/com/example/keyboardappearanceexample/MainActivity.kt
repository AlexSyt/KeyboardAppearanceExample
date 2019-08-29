package com.example.keyboardappearanceexample

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var animator: BaseKeyboardAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContentView(R.layout.activity_main)

        showBottomSheetView.setOnClickListener {
            showBottomSheet()
        }
        animateCheckBox.setOnCheckedChangeListener { _, isChecked ->
            toggleAnimation(isChecked)
        }
        customPaddingCheckBox.setOnCheckedChangeListener { _, isChecked ->
            updateAnimator(isChecked)
        }

        updateAnimator(customPaddingCheckBox.isChecked)
    }

    private fun showBottomSheet() =
        BottomSheetDialog(
            this,
            animateCheckBox.isChecked,
            customPaddingCheckBox.isChecked
        ).show()

    private fun toggleAnimation(show: Boolean) = if (show) animator?.start() else animator?.stop()

    private fun updateAnimator(useCustomPadding: Boolean) {
        animator?.stop()
        animator = if (useCustomPadding) {
            CustomizableKeyboardAnimator(window, R.id.containerView)
        } else {
            SimpleKeyboardAnimator(window)
        }
        if (animateCheckBox.isChecked) {
            animator?.start()
        }
    }
}