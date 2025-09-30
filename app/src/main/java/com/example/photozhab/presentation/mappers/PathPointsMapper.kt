package com.example.photozhab.presentation.mappers

import com.example.photozhab.domain.model.canvas.PathPoints as PathPointsDomain
import com.example.photozhab.presentation.model.PathPoints

/**
 * [PathPointsDomain] -> [PathPoints]
 *
 * @receiver [PathPointsDomain].
 * @return [PathPoints].
 */
fun PathPointsDomain.toPresentationLayer(): PathPoints {
    return PathPoints(
        moveToX = moveToX,
        moveToY = moveToY,
        lineToX = lineToX,
        lineToY = lineToY
    )
}

/**
 * [PathPoints] -> [PathPointsDomain]
 *
 * @receiver [PathPoints].
 * @return [PathPointsDomain].
 */

fun PathPoints.toDomainLayer(): PathPointsDomain {
    return PathPointsDomain(
        moveToX = moveToX,
        moveToY = moveToY,
        lineToX = lineToX,
        lineToY = lineToY
    )
}