package com.example.photozhab.domain.interfaces.repository.local

import com.example.photozhab.data.local.entity.Canvas

interface CanvasRepository {

    fun saveCanvas(canvas: Canvas): Boolean

    fun deleteCanvas(id: Int): Boolean

    fun getCanvasById(id: Int): Canvas?

    fun saveTempCanvas(canvas: Canvas): Boolean

    fun getTempCanvas(): Canvas?
}