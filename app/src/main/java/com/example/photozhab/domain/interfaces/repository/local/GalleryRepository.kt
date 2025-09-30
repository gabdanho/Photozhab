package com.example.photozhab.domain.interfaces.repository.local

import android.graphics.Bitmap

/**
 * Репозиторий для сохранения проектов в галерею устройства.
 */
interface GalleryRepository {

    /**
     * Сохраняет изображение Bitmap в галерею.
     * @param bitmap Изображение для сохранения.
     */
    suspend fun saveProjectInGallery(bitmap: Bitmap)
}