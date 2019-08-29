package com.example.keyboardappearanceexample

import android.view.View
import android.view.WindowInsets
import kotlin.math.min

class BottomInsetDetector(
    private val onOffsetChanged: (Int) -> Unit
) : View.OnApplyWindowInsetsListener {

    private var previousOffset: Int = 0

    override fun onApplyWindowInsets(view: View, insets: WindowInsets): WindowInsets {
        var result = insets
        val offset = when {
            insets.systemWindowInsetBottom < insets.stableInsetBottom -> insets.systemWindowInsetBottom
            else -> insets.systemWindowInsetBottom - insets.stableInsetBottom
        }
        if (offset != previousOffset) {
            onOffsetChanged(offset)
            previousOffset = offset
            result = insets.consumeBottomInset()
        }
        return view.onApplyWindowInsets(result)
    }

    private fun WindowInsets.consumeBottomInset(): WindowInsets =
        replaceSystemWindowInsets(
            systemWindowInsetLeft,
            systemWindowInsetTop,
            systemWindowInsetRight,
            min(systemWindowInsetBottom, stableInsetBottom)
        )
}