package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.model.Offset
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.data.local.model.figures.Polygon
import com.example.photozhab.domain.model.canvas.figures.Polygon as PolygonDomain

fun PolygonDomain.toDataLayer(): Polygon {
    return Polygon(
        color = color,
        offset = Offset(offset.x, offset.y),
        angle = angle,
        scale = scale,
        vertices = vertices
    )
}

fun Polygon.toDomainLayer(): PolygonDomain {
    return PolygonDomain(
        color = color,
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        scale = scale,
        vertices = vertices
    )
}