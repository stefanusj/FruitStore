package com.stefanusj.fruitstore.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.GetRequest
import coil.request.LoadRequest

fun Drawable?.toBitmap(isMutable: Boolean = false): Bitmap =
    (this as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, isMutable)

fun generatePalette(
    context: Context,
    image: String,
    onComplete: (Palette?) -> Unit
) {
    val request = LoadRequest.Builder(context)
        .data(image)
        .target { drawable ->
            val bitmap = drawable.toBitmap()
            Palette.from(bitmap).generate { onComplete(it) }
        }
        .build()
    Coil.imageLoader(context).execute(request)
}

suspend fun generatePalette(
    context: Context,
    image: String
): Palette {

    val request = GetRequest.Builder(context).data(image).build()
    val result = Coil.imageLoader(context).execute(request)
    val bitmap = result.drawable.toBitmap()

    return Palette.from(bitmap).generate()
}

fun getPlatformColor(
    context: Context,
    image: String,
    darken: Float = 0.5f,
    onComplete: (Int) -> Unit
) =
    generatePalette(context, image) {
        checkNotNull(it)
        val swatch = it.dominantSwatch
        onComplete(swatch?.rgb?.darken(darken) ?: 0)
    }

suspend fun getPlatformColor(
    context: Context,
    image: String,
    darken: Float = 0.5f
): Int {
    val palette = generatePalette(context, image)
    val swatch = palette.dominantSwatch
    return swatch?.rgb?.darken(darken) ?: 0
}