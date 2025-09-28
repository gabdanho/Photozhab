package com.example.photozhab.data.repository.impl

import com.example.photozhab.data.local.dao.CanvasDao
import com.example.photozhab.data.mappers.toDataLayer
import com.example.photozhab.data.mappers.toDomainLayer
import com.example.photozhab.domain.interfaces.repository.local.CanvasRepository
import com.example.photozhab.domain.model.canvas.Canvas
import com.example.photozhab.domain.model.canvas.CanvasInfo

class CanvasRepositoryImpl(
    private val dao: CanvasDao,
) : CanvasRepository {

    override suspend fun saveCanvas(canvas: Canvas): Boolean {
        return try {
            dao.insertCanvas(canvas.toDataLayer())
            true
        } catch (_: Exception) {
            false
        }
    }

    override suspend fun deleteCanvas(id: Int): Boolean {
        return try {
            dao.deleteCanvasById(id)
            true
        } catch (_: Exception) {
            false
        }
    }

    override suspend fun getCanvasById(id: Int): Canvas? {
        return try {
            dao.getCanvasById(id).toDomainLayer()
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun saveTempCanvas(canvas: Canvas): Boolean {
        return try {
            val mappedCanvas = canvas.copy(id = 1, name = "temp").toDataLayer()
            dao.insertCanvas(mappedCanvas)
            true
        } catch (_: Exception) {
            false
        }
    }

    override suspend fun getTempCanvas(): Canvas? {
        return try {
            dao.getCanvasById(id = 1).toDomainLayer()
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun getCanvases(): List<CanvasInfo> {
        return dao.getCanvasesInfo().map { it.toDomainLayer() }
    }
}