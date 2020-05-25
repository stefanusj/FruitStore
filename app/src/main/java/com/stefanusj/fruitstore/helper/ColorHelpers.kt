package com.stefanusj.fruitstore.helper

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat

fun Int.toColor(context: Context) =
    ContextCompat.getColor(context, this)

fun Int.toColorDrawable(context: Context) =
    ColorDrawable(toColor(context))

fun Int.toColorStateList() =
    ColorStateList.valueOf(this)

/**
 * Returns darker version of specified `color`.
 * Credit: https://stackoverflow.com/a/4928826/8169138
 */
fun Int.darken(factor: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[2] *= factor

    return Color.HSVToColor(hsv)
}