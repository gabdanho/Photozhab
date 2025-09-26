package com.example.photozhab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photozhab.data.local.entity.Canvas

@Dao
interface CanvasDao {

    @Query("SELECT * FROM canvas WHERE id = :id")
    fun getCanvasById(id: Int): Canvas

    @Query("DELETE FROM canvas WHERE id = :id")
    fun deleteCanvasById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCanvas(canvas: Canvas)
}