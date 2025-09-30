package com.example.photozhab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photozhab.data.local.entity.Canvas
import com.example.photozhab.data.local.entity.Canvas.Companion.TEMP_CANVAS_ID
import com.example.photozhab.data.local.model.CanvasInfo

@Dao
interface CanvasDao {

    @Query("SELECT * FROM canvas WHERE id = :id")
    suspend fun getCanvasById(id: Int): Canvas?

    @Query("DELETE FROM canvas WHERE id = :id")
    suspend fun deleteCanvasById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCanvas(canvas: Canvas)

    @Query("SELECT id, name FROM canvas WHERE id != $TEMP_CANVAS_ID")
    suspend fun getCanvasesInfo(): List<CanvasInfo>
}