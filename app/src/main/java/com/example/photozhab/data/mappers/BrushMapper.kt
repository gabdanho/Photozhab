package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.model.Figure.Brush
import com.example.photozhab.domain.model.canvas.Figure.Brush as BrushDomain

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