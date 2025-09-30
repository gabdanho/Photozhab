package com.example.photozhab.data.repository.impl

import android.content.ContentResolver
import android.graphics.Bitmap
import com.example.photozhab.data.utils.MediaStoreSaver
import com.example.photozhab.domain.interfaces.repository.local.GalleryRepository

/**
 * Репозиторий для работы с галереей.
 *
 * @param resolver ContentResolver для сохранения bitmap в галерею.
 */
class GalleryRepositoryImpl(
    private val resolver: ContentResolver,
) : GalleryRepository {

    override suspend fun saveProjectInGallery(bitmap: Bitmap) {
        MediaStoreSaver.saveBitmapToGallery(
            bitmap = bitmap,
            resolver = resolver
        )
    }
}