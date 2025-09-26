package com.example.photozhab.data.local.converters

import androidx.room.TypeConverter
import com.example.photozhab.data.local.model.figures.Figure
import kotlinx.serialization.json.Json

class FiguresConverter {
    private val json = Json { encodeDefaults = true; ignoreUnknownKeys = true }

    @TypeConverter
    fun fromFigures(figures: List<Figure>): String {
        return json.encodeToString(figures)
    }

    @TypeConverter
    fun toFigures(data: String): List<Figure> {
        return json.decodeFromString(data)
    }
}