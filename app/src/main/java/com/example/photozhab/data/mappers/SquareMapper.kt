package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.model.Offset
import com.example.photozhab.data.local.model.figures.Square
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.figures.Square as SquareDomain

fun SquareDomain.toDataLayer(): Square {
    return Square(
        color = color,
        offset = Offset(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}

fun Square.toDomainLayer(): SquareDomain {
    return SquareDomain(
        color = color,
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}