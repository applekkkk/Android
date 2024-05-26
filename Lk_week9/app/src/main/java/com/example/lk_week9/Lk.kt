package com.example.lk_week9

import android.graphics.Bitmap
import kotlin.math.pow

fun Bitmap.applyGammaCorrection(gamma: Double): Bitmap {
    val width = this.width
    val height = this.height
    val result = Bitmap.createBitmap(width, height, this.config)
    for (x in 0 until width) {
        for (y in 0 until height) {
            val color = this.getPixel(x, y)
            val red = color and 0xFF
            val green = (color shr 8) and 0xFF
            val blue = (color shr 16) and 0xFF
            val alpha = color ushr 24

            val newRed = (red.toDouble() / 255.0).pow(gamma) * 255.0
            val newGreen = (green.toDouble() / 255.0).pow(gamma) * 255.0
            val newBlue = (blue.toDouble() / 255.0).pow(gamma) * 255.0

            val newColor = (alpha shl 24) or ((newRed.toInt() and 0xFF) shl 16) or
                    ((newGreen.toInt() and 0xFF) shl 8) or (newBlue.toInt() and 0xFF)

            result.setPixel(x, y, newColor)
        }
    }
    return result
}
class test {
}