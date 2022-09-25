package com.example.test.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

/**
 * Наследник [Fragment] на композе
 */
abstract class ComposeFragment : Fragment() {

    protected abstract fun composeViewFactory(): ComposeView

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return composeViewFactory()
    }
}