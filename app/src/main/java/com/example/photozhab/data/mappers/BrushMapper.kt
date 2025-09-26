package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.model.PointF
import com.example.photozhab.domain.model.canvas.PointF as PointFDomain
import com.example.photozhab.data.local.model.figures.Brush
import com.example.photozhab.domain.model.canvas.figures.Brush as BrushDomain

fun BrushDomain.toDataLayer(): Brush {
    return Brush(
        color = color,
        brushWidth = brushWidth,
        points = points.map { PointF(it.x, it.y) }
    )
}

fun Brush.toDomainLayer(): BrushDomain {
    return BrushDomain(
        color = color,
        brushWidth = brushWidth,
        points = points.map { PointFDomain(it.x, it.y) }
    )
}