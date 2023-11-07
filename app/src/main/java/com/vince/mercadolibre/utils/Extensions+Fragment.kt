package com.vince.mercadolibre.utils

import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.vince.mercadolibre.utils.views.FragmentViewBindingDelegate

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

fun Fragment.showToast(@StringRes message: Int, length: Int = Toast.LENGTH_LONG) {
    requireContext().showToast(message, length)
}