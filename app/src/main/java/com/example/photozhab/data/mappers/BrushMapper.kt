package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.model.figures.Brush
import com.example.photozhab.domain.model.canvas.figures.Brush as BrushDomain

fun BrushDomain.toDataLayer(): Brush {
    return Brush(
        color = color,
        brushWidth = brushWidth,
        path = path.map { it.toDataLayer() }
    )
}

fun Brush.toDomainLayer(): BrushDomain {
    return BrushDomain(
        color = color,
        brushWidth = brushWidth,
        path = path.map { it.toDomainLayer() }
    )
}