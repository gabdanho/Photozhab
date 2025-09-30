package com.example.photozhab.presentation.mappers.figures

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.graphics.toColorLong
import com.example.photozhab.domain.model.canvas.Figure.Circle as CircleDomain
import com.example.photozhab.presentation.model.Figure.Circle
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain

/**
 * [Circle] -> [CircleDomain]
 *
 * @receiver [CircleDomain].
 * @return [Circle].
 */
fun CircleDomain.toPresentationLayer(): Circle {
    return Circle(
        color = Color.fromColorLong(color),
        offset = Offset(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}

/**
 * [CircleDomain] -> [Circle]
 *
 * @receiver [Circle].
 * @return [CircleDomain].
 */
fun Circle.toDomainLayer(): CircleDomain {
    return CircleDomain(
        color = color.toColorLong(),
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}