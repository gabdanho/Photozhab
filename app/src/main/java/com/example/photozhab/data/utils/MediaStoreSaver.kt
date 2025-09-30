package com.example.photozhab.data.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import java.io.IOException

/**
 * Утилитный объект для сохранения Bitmap изображений в галерею устройства.
 */
object MediaStoreSaver {

    /**
     * Сохраняет Bitmap в папку "Photozhab" в галерее.
     *
     * @param bitmap Bitmap изображение для сохранения.
     * @param resolver ContentResolver для доступа к хранилищу.
     * @throws IOException если произошла ошибка при сохранении.
     */
    fun saveBitmapToGallery(bitmap: Bitmap, resolver: ContentResolver) {
        val fileName = "photozhab_${System.currentTimeMillis()}.png"
        val mimeType = "image/png"
        val folderName = "Photozhab"

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, mimeType)
            put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/$folderName")
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        try {
            uri?.let {
                resolver.openOutputStream(it).use { outputStream ->
                    outputStream?.let {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    }
                }

                values.clear()
                values.put(MediaStore.Images.Media.IS_PENDING, 0)
                resolver.update(uri, values, null, null)
            }
        } catch (e: Exception) {
            throw IOException("Error saving bitmap", e)
        }
    }
}