package com.inhauniv.hackathon.domain.util

import java.text.NumberFormat
import java.util.Locale

val String.Companion.Empty
    get() = ""

fun formatAmount(number: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale.KOREA)
    val amountStr = formatter.format(number)
    return amountStr+"원"
}