package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.entity.Canvas
import com.example.photozhab.domain.model.canvas.Canvas as CanvasDomain

fun CanvasDomain.toDataLayer(): Canvas {
    return Canvas(
        id = id,
        name = name,
        figures = figures.map { it.toDataLayer() },
        background = backgroundColor
    )
}

fun Canvas.toDomainLayer(): CanvasDomain {
    return CanvasDomain(
        id = id,
        name = name,
        figures = figures.map { it.toDomainLayer() },
        backgroundColor = background
    )
}