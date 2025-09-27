package com.example.photozhab.presentation.mappers

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.graphics.toColorLong
import com.example.photozhab.presentation.model.PathData
import com.example.photozhab.presentation.model.Figure.Brush
import com.example.photozhab.domain.model.canvas.Figure.Brush as BrushDomain

fun BrushDomain.toPresentationLayer(): Brush {
    return Brush(
        color = Color.fromColorLong(color),
        brushWidth = brushWidth,
        path = PathData(pathPointsList = path.map { it.toPresentationLayer() })
    )
}

fun Brush.toDomainLayer(): BrushDomain {
    return BrushDomain(
        color = color.toColorLong(),
        brushWidth = brushWidth,
        path = path.pathPointsList.map { it.toDomainLayer() }
    )
}