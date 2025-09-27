package com.example.photozhab.data.mappers

import com.example.photozhab.domain.model.canvas.PathPoints as PathPointsDomain
import com.example.photozhab.data.local.model.PathPoints

fun PathPointsDomain.toDataLayer(): PathPoints {
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