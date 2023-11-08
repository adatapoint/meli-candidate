package com.vince.mercadolibre.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun getFormattedMoney(moneyAmount: BigDecimal): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    format.minimumFractionDigits = 0
    return format.format(moneyAmount)
}
