package com.example.photozhab.presentation.utils

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.GraphicsLayer

suspend fun GraphicsLayer.saveToBitmap(): Bitmap {
    return this.toImageBitmap().asAndroidBitmap()
}