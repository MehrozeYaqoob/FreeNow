package com.freenow.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

object Utils {
    fun bitmapDescriptorFromVector(
        context: Context,
        @DrawableRes vectorDrawableResourceId: Int,
        resourceId: Int
    ): Bitmap? {
        val background = ContextCompat.getDrawable(context, resourceId)
        background?.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable?.setBounds(
            40,
            40,
            vectorDrawable.intrinsicWidth + 40,
            vectorDrawable.intrinsicHeight + 40
        )
        val bitmap = background?.let {
            Bitmap.createBitmap(
                it.intrinsicWidth,
                it.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = bitmap?.let { Canvas(it) }
        if (canvas != null) {
            background.draw(canvas)
            vectorDrawable?.draw(canvas)
        }
        return bitmap
    }
}
