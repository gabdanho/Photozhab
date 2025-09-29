package com.example.photozhab.domain.interfaces.repository.local

import android.graphics.Bitmap

interface GalleryRepository {

    suspend fun saveProjectInGallery(bitmap: Bitmap)
}