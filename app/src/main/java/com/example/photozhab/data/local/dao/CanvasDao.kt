package com.example.photozhab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photozhab.data.local.entity.Canvas
import com.example.photozhab.data.local.entity.Canvas.Companion.TEMP_CANVAS_ID
import com.example.photozhab.data.local.model.CanvasInfo

/**
 * DAO для работы с таблицей Canvas.
 */
@Dao
interface CanvasDao {

    /**
     * Получает Canvas по идентификатору.
     *
     * @param id Идентификатор Canvas.
     * @return Canvas или null, если не найден.
     */
    @Query("SELECT * FROM canvas WHERE id = :id")
    suspend fun getCanvasById(id: Int): Canvas?

    /**
     * Удаляет Canvas по идентификатору.
     *
     * @param id Идентификатор Canvas для удаления.
     */
    @Query("DELETE FROM canvas WHERE id = :id")
    suspend fun deleteCanvasById(id: Int)

    /**
     * Вставляет или обновляет Canvas в базе данных.
     *
     * @param canvas Объект Canvas для сохранения.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCanvas(canvas: Canvas)

    /**
     * Получает информацию о всех Canvas, кроме временного.
     *
     * @return Список CanvasInfo.
     */
    @Query("SELECT id, name FROM canvas WHERE id != $TEMP_CANVAS_ID")
    suspend fun getCanvasesInfo(): List<CanvasInfo>
}