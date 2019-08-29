package com.example.keyboardappearanceexample

import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.updatePadding
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager

class CustomizableKeyboardAnimator(
    window: Window,
    animatedViewId: Int = Window.ID_ANDROID_CONTENT
) : BaseKeyboardAnimator(window) {

    private val animatedView: View? by lazy(LazyThreadSafetyMode.NONE) {
        window.decorView.findViewById<View>(animatedViewId)
    }

    override val insetsListener: View.OnApplyWindowInsetsListener
        get() = BottomInsetDetector { offset ->
            (animatedView?.parent as? ViewGroup)?.let { sceneRoot ->
                TransitionManager.beginDelayedTransition(sceneRoot, ChangeBounds())
                animatedView?.updatePadding(bottom = offset)
            }
        }
}