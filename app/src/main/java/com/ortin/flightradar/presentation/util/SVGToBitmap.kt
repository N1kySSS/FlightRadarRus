package com.ortin.flightradar.presentation.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat

fun vectorDrawableToBitmap(context: Context, drawableResId: Int): Bitmap {
    val drawable = ContextCompat.getDrawable(context, drawableResId)
        ?: throw IllegalArgumentException("Drawable not found")

    val width = drawable.intrinsicWidth
    val height = drawable.intrinsicHeight

    if (width <= 0 || height <= 0) {
        throw IllegalArgumentException("Drawable has invalid intrinsic dimensions")
    }

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}
