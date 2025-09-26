package com.example.photozhab.presentation.mappers

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.graphics.toColorLong
import com.example.photozhab.presentation.model.PathData
import com.example.photozhab.presentation.model.PointF
import com.example.photozhab.domain.model.canvas.PointF as PointFDomain
import com.example.photozhab.presentation.model.figures.Brush
import com.example.photozhab.domain.model.canvas.figures.Brush as BrushDomain

fun BrushDomain.toPresentationLayer(): Brush {
    return Brush(
        color = Color.fromColorLong(color),
        brushWidth = brushWidth,
        pathData = PathData(points.map { PointF(it.x, it.y) })
    )
}

fun Brush.toDomainLayer(): BrushDomain {
    return BrushDomain(
        color = color.toColorLong(),
        brushWidth = brushWidth,
        points = pathData.points.map { PointFDomain(it.x, it.y) }
    )
}