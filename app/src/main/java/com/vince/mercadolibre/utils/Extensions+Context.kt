package com.vince.mercadolibre.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes message: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {},
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)
