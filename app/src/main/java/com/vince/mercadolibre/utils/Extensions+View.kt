package com.vince.mercadolibre.utils

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.vince.mercadolibre.utils.views.SafeClickListener

fun ViewHolder.getFormattedMoneyWithCurrency(
    moneyAmount: Int,
    currency: String
): String = itemView.context.getFormattedMoneyWithCurrency(moneyAmount, currency)

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(onSafeClick)
    setOnClickListener(safeClickListener)
}

fun ImageView.loadImage(
    uri: String,
    errorResId: Int? = null,
) {
    this.load(uri) {
        errorResId?.let {
            fallback(it)
            error(it)
        }
    }
}

fun View.setAsVisible() {
    this.visibility = View.VISIBLE
}

fun View.setAsGone() {
    this.visibility = View.GONE
}