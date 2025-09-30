package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.entity.Canvas
import com.example.photozhab.data.mappers.figures.toDataLayer
import com.example.photozhab.data.mappers.figures.toDomainLayer
import com.example.photozhab.domain.model.canvas.Canvas as CanvasDomain

/**
 * [CanvasDomain] -> [Canvas]
 *
 * @receiver [CanvasDomain].
 * @return [Canvas].
 */
fun CanvasDomain.toDataLayer(): Canvas {
    return Canvas(
        id = id,
        name = name,
        figures = figures.map { it.toDataLayer() },
        background = background
    )
}

/**
 * [Canvas] -> [CanvasDomain]
 *
 * @receiver [Canvas].
 * @return [CanvasDomain].
 */
fun Canvas.toDomainLayer(): CanvasDomain {
    return CanvasDomain(
        id = id,
        name = name,
        figures = figures.map { it.toDomainLayer() },
        background = background
    )
}