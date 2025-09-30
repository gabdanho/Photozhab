package com.example.photozhab.data.mappers.figures

import com.example.photozhab.data.local.model.Offset
import com.example.photozhab.data.local.model.Figure.Square
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.Figure.Square as SquareDomain

/**
 * [SquareDomain] -> [Square]
 *
 * @receiver [SquareDomain].
 * @return [Square].
 */
fun SquareDomain.toDataLayer(): Square {
    return Square(
        color = color,
        offset = Offset(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}

/**
 * [Square] -> [SquareDomain]
 *
 * @receiver [Square].
 * @return [SquareDomain].
 */
fun Square.toDomainLayer(): SquareDomain {
    return SquareDomain(
        color = color,
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}