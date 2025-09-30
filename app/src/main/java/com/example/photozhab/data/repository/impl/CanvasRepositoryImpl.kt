package com.example.photozhab.data.repository.impl

import com.example.photozhab.data.local.dao.CanvasDao
import com.example.photozhab.data.local.entity.Canvas.Companion.TEMP_CANVAS_ID
import com.example.photozhab.data.local.entity.Canvas.Companion.TEMP_CANVAS_NAME
import com.example.photozhab.data.mappers.toDataLayer
import com.example.photozhab.data.mappers.toDomainLayer
import com.example.photozhab.domain.interfaces.repository.local.CanvasRepository
import com.example.photozhab.domain.model.canvas.Canvas
import com.example.photozhab.domain.model.canvas.CanvasInfo
import java.io.IOException

class CanvasRepositoryImpl(
    private val dao: CanvasDao,
) : CanvasRepository {

    override suspend fun saveCanvas(canvas: Canvas) {
        try {
            dao.insertCanvas(canvas.toDataLayer())
        } catch (e: Exception) {
            throw IOException("Error to save canvas", e)
        }
    }

    override suspend fun deleteCanvas(id: Int) {
        try {
            dao.deleteCanvasById(id)
        } catch (e: Exception) {
            throw IOException("Error to delete canvas", e)
        }
    }

    override suspend fun getCanvasById(id: Int): Canvas? {
        return try {
            dao.getCanvasById(id)?.toDomainLayer()
        } catch (e: Exception) {
            throw IOException("Error to get canvas", e)
        }
    }

    override suspend fun saveTempCanvas(canvas: Canvas) {
        try {
            val mappedCanvas =
                canvas.copy(id = TEMP_CANVAS_ID, name = TEMP_CANVAS_NAME).toDataLayer()
            dao.insertCanvas(mappedCanvas)
        } catch (e: Exception) {
            throw IOException("Error to save temp canvas", e)
        }
    }

    override suspend fun getTempCanvas(): Canvas? {
        return try {
            dao.getCanvasById(id = TEMP_CANVAS_ID)?.toDomainLayer()
        } catch (e: Exception) {
            throw IOException("Error to get temp canvas", e)
        }
    }

    override suspend fun getCanvases(): List<CanvasInfo> {
        return dao.getCanvasesInfo().map { it.toDomainLayer() }
    }
}