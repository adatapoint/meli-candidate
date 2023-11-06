package com.vince.mercadolibre.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes message: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}