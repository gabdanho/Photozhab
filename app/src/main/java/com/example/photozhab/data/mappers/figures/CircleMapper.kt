package com.example.photozhab.data.mappers.figures

import com.example.photozhab.data.local.model.Offset
import com.example.photozhab.data.local.model.Figure.Circle
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.Figure.Circle as CircleDomain

/**
 * [Circle] -> [CircleDomain]
 *
 * @receiver [CircleDomain].
 * @return [Circle].
 */
fun CircleDomain.toDataLayer(): Circle {
    return Circle(
        color = color,
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
        color = color,
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        scale = scale
    )
}