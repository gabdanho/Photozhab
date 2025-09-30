package com.example.photozhab.domain.interfaces.repository.local

import com.example.photozhab.domain.model.canvas.Canvas
import com.example.photozhab.domain.model.canvas.CanvasInfo

/**
 * Репозиторий для работы с холстами (Canvas) в локальном хранилище.
 */
interface CanvasRepository {

    /**
     * Сохраняет холст.
     * @param canvas Холст для сохранения.
     */
    suspend fun saveCanvas(canvas: Canvas)

    /**
     * Удаляет холст по его идентификатору.
     * @param id Идентификатор холста.
     */
    suspend fun deleteCanvas(id: Int)

    /**
     * Получает холст по его идентификатору.
     * @param id Идентификатор холста.
     * @return Холст или null, если не найден.
     */
    suspend fun getCanvasById(id: Int): Canvas?

    /**
     * Сохраняет временный холст (например, для автосохранения).
     * @param canvas Временный холст.
     */
    suspend fun saveTempCanvas(canvas: Canvas)

    /**
     * Получает временный холст.
     * @return Временный холст или null.
     */
    suspend fun getTempCanvas(): Canvas?

    /**
     * Получает список информации о всех сохранённых холстах.
     * @return Список CanvasInfo.
     */
    suspend fun getCanvases(): List<CanvasInfo>
}