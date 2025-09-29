package com.example.photozhab.presentation.mappers.figures

import com.example.photozhab.domain.model.canvas.Canvas
import com.example.photozhab.presentation.model.CanvasSave

fun Canvas.toPresentationLayer(): CanvasSave {
    return CanvasSave(
        id = id,
        name = name,
        figures = figures.map { it.toPresentationLayer() },
        backgroundColor = backgroundColor
    )
}

fun CanvasSave.toDomainLayer(): Canvas {
    return Canvas(
        id = id,
        name = name,
        figures = figures.map { it.toDomainLayer() },
        backgroundColor = backgroundColor
    )
}