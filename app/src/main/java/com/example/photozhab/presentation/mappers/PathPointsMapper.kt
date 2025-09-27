package com.example.photozhab.presentation.mappers

import com.example.photozhab.domain.model.canvas.PathPoints as PathPointsDomain
import com.example.photozhab.presentation.model.PathPoints

fun PathPointsDomain.toPresentationLayer(): PathPoints {
    return PathPoints(
        moveToX = moveToX,
        moveToY = moveToY,
        lineToX = lineToX,
        lineToY = lineToY
    )
}

fun PathPoints.toDomainLayer(): PathPointsDomain {
    return PathPointsDomain(
        moveToX = moveToX,
        moveToY = moveToY,
        lineToX = lineToX,
        lineToY = lineToY
    )
}