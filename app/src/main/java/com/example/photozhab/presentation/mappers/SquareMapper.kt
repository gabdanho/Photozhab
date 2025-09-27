package com.example.photozhab.presentation.mappers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.graphics.toColorLong
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.Figure.Square as SquareDomain
import com.example.photozhab.presentation.model.Figure.Square

fun SquareDomain.toPresentationLayer(): Square {
    return Square(
        color = Color.fromColorLong(color),
        offset = Offset(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}

fun Square.toDomainLayer(): SquareDomain {
    return SquareDomain(
        color = color.toColorLong(),
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}