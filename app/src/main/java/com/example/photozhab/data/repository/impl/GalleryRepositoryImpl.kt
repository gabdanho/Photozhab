package com.example.photozhab.data.repository.impl

import android.content.Context
import android.graphics.Bitmap
import com.example.photozhab.data.utils.MediaStoreSaver
import com.example.photozhab.domain.interfaces.repository.local.GalleryRepository

class GalleryRepositoryImpl(
    private val context: Context,
) : GalleryRepository {

    override suspend fun saveProjectInGallery(bitmap: Bitmap) {
        MediaStoreSaver.saveBitmapToGallery(
            bitmap = bitmap,
            context = context
        )
    }
}