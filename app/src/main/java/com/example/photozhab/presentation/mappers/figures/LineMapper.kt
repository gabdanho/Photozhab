package com.example.photozhab.presentation.mappers.figures

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.graphics.toColorLong
import androidx.compose.ui.unit.dp
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.Figure.Line as LineDomain
import com.example.photozhab.presentation.model.Figure.Line

fun LineDomain.toPresentationLayer(): Line {
    return Line(
        color = Color.fromColorLong(color),
        offset = Offset(offset.x, offset.y),
        angle = angle,
        widthDp = widthDp.dp,
        lineWidth = lineWidth
    )
}

fun Line.toDomainLayer(): LineDomain {
    return LineDomain(
        color = color.toColorLong(),
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        widthDp = widthDp.value.toInt(),
        lineWidth = lineWidth
    )
}