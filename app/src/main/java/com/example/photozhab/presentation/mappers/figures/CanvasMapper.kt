package com.example.photozhab.presentation.mappers.figures

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.graphics.toColorLong
import com.example.photozhab.domain.model.canvas.Canvas
import com.example.photozhab.presentation.model.CanvasSave

fun Canvas.toPresentationLayer(): CanvasSave {
    return CanvasSave(
        id = id,
        name = name,
        figures = figures.map { it.toPresentationLayer() },
        backgroundColor = Color.fromColorLong(backgroundColor)
    )
}

fun CanvasSave.toDomainLayer(): Canvas {
    return Canvas(
        id = id,
        name = name,
        figures = figures.map { it.toDomainLayer() },
        backgroundColor = backgroundColor.toColorLong()
    )
}