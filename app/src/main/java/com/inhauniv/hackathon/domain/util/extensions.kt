package com.inhauniv.hackathon.domain.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

val String.Companion.Empty
    get() = ""

fun formatAmount(number: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale.KOREA)
    val amountStr = formatter.format(number)
    return amountStr+"원"
}

fun formattedDate(date: String):String {
    val formatter = DateTimeFormatter.ofPattern("M월 d일 HH:mm", Locale("ko"))
    val dateTime = LocalDateTime.parse(date)
    return dateTime.format(formatter)
}

fun Context.getDrawableCompat(@DrawableRes drawable: Int): Drawable =
    requireNotNull(ContextCompat.getDrawable(this, drawable))