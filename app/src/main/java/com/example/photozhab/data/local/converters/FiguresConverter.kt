package com.example.photozhab.data.local.converters

import androidx.room.TypeConverter
import com.example.photozhab.data.local.model.Figure
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

object FiguresConverter {
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        classDiscriminator = "type"
    }

    @TypeConverter
    fun fromFigures(figures: List<Figure>): String {
        return json.encodeToString(ListSerializer(Figure.serializer()), figures)
    }

    @TypeConverter
    fun toFigures(data: String): List<Figure> {
        return json.decodeFromString(ListSerializer(Figure.serializer()), data)
    }
}