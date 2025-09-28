package com.example.photozhab.data.mappers.figures

import com.example.photozhab.data.local.model.Offset
import com.example.photozhab.data.local.model.Figure.Triangle
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.Figure.Triangle as TriangleDomain

fun TriangleDomain.toDataLayer(): Triangle {
    return Triangle(
        color = color,
        offset = Offset(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}

fun Triangle.toDomainLayer(): TriangleDomain {
    return TriangleDomain(
        color = color,
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}