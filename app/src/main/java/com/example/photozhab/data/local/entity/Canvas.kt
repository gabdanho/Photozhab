package com.example.photozhab.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.photozhab.data.local.converters.FiguresConverter
import com.example.photozhab.data.local.entity.Canvas.Companion.TABLE_CANVAS
import com.example.photozhab.data.local.model.Figure

/**
 * Сущность Canvas для Room.
 *
 * @param id Идентификатор Canvas.
 * @param name Название Canvas.
 * @param figures Список фигур на Canvas.
 * @param background Цвет фона Canvas в формате Long.
 */
@Entity(tableName = TABLE_CANVAS)
@TypeConverters(FiguresConverter::class)
data class Canvas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val figures: List<Figure> = emptyList(),
    val background: Long = 0L,
) {
    companion object {
        const val TABLE_CANVAS = "canvas"
        const val TEMP_CANVAS_ID = 1
        const val TEMP_CANVAS_NAME = "temp"
    }
}
