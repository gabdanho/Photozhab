package com.example.photozhab.data.mappers.figures

import com.example.photozhab.data.local.model.Figure.Brush
import com.example.photozhab.data.mappers.toDataLayer
import com.example.photozhab.data.mappers.toDomainLayer
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