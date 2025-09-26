package com.example.photozhab.data.repository.impl

import com.example.photozhab.data.local.dao.CanvasDao
import com.example.photozhab.data.local.entity.Canvas
import com.example.photozhab.domain.interfaces.repository.local.CanvasRepository

class CanvasRepositoryImpl(
    private val dao: CanvasDao
) : CanvasRepository {

    override fun saveCanvas(canvas: Canvas): Boolean {
        return try {
            dao.insertCanvas(canvas)
            true
        } catch (_: Exception) {
            false
        }
    }

    override fun deleteCanvas(id: Int): Boolean {
        return try {
            dao.deleteCanvasById(id)
            true
        } catch (_: Exception) {
            false
        }
    }

    override fun getCanvasById(id: Int): Canvas? {
        return dao.getCanvasById(id)
    }

    override fun saveTempCanvas(canvas: Canvas): Boolean {
        return try {
            dao.insertCanvas(canvas.copy(id = 0))
            true
        } catch (_: Exception) {
            false
        }
    }

    override fun getTempCanvas(): Canvas? {
        return dao.getCanvasById(id = 0)
    }
}