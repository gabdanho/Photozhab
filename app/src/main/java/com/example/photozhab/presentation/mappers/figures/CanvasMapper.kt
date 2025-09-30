package com.example.photozhab.presentation.mappers.figures

import com.example.photozhab.domain.model.canvas.Canvas
import com.example.photozhab.presentation.model.CanvasSave

/**
 * [Canvas] -> [CanvasSave]
 *
 * @receiver [Canvas].
 * @return [CanvasSave].
 */
fun Canvas.toPresentationLayer(): CanvasSave {
    return CanvasSave(
        id = id,
        name = name,
        figures = figures.map { it.toPresentationLayer() },
        background = background
    )
}

/**
 * [CanvasSave] -> [Canvas]
 *
 * @receiver [CanvasSave].
 * @return [Canvas].
 */
fun CanvasSave.toDomainLayer(): Canvas {
    return Canvas(
        id = id,
        name = name,
        figures = figures.map { it.toDomainLayer() },
        background = background
    )
}