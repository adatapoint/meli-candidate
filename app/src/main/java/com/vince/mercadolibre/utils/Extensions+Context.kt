package com.vince.mercadolibre.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import com.vince.mercadolibre.R

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

fun Context.getFormattedMoneyWithCurrency(
    moneyAmount: Int,
    currency: String
): String = String.format(
    getString(R.string.money_amount),
    getFormattedMoney(moneyAmount.toBigDecimal()),
    currency,
)

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

fun <T> Context.shouldImplement(myInterface: Class<*>): T {
    if (myInterface.isAssignableFrom(this::class.java)) {
        return this as T
    } else {
        throw InterfaceNotImplementedException(
            this.javaClass.simpleName,
            myInterface.simpleName,
        )
    }
}