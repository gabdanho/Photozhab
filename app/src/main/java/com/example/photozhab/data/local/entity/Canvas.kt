package com.example.photozhab.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.photozhab.data.local.converters.FiguresConverter
import com.example.photozhab.data.local.entity.Canvas.Companion.TABLE_CANVAS
import com.example.photozhab.data.local.model.Figure

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
        private const val TABLE_CANVAS = "canvas"
    }
}
