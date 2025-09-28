package com.example.photozhab.domain.interfaces.repository.local

import com.example.photozhab.domain.model.canvas.Canvas
import com.example.photozhab.domain.model.canvas.CanvasInfo

interface CanvasRepository {

    suspend fun saveCanvas(canvas: Canvas): Boolean

    suspend fun deleteCanvas(id: Int): Boolean

    suspend fun getCanvasById(id: Int): Canvas?

    suspend fun saveTempCanvas(canvas: Canvas): Boolean

    suspend fun getTempCanvas(): Canvas?

    suspend fun getCanvases(): List<CanvasInfo>
}