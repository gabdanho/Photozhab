package com.example.photozhab.presentation.mappers.figures

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.graphics.toColorLong
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.Figure.Polygon as PolygonDomain
import com.example.photozhab.presentation.model.Figure.Polygon

/**
 * [PolygonDomain] -> [Polygon]
 *
 * @receiver [PolygonDomain].
 * @return [Polygon].
 */

fun PolygonDomain.toPresentationLayer(): Polygon {
    return Polygon(
        color = Color.fromColorLong(color),
        offset = Offset(offset.x, offset.y),
        angle = angle,
        vertices = vertices,
        scale = scale
    )
}

/**
 * [Polygon] -> [PolygonDomain]
 *
 * @receiver [Polygon].
 * @return [PolygonDomain].
 */
fun Polygon.toDomainLayer(): PolygonDomain {
    return PolygonDomain(
        color = color.toColorLong(),
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        vertices = vertices,
        scale = scale
    )
}