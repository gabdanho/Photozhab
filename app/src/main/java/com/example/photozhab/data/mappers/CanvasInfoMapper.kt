package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.model.CanvasInfo
import com.example.photozhab.domain.model.canvas.CanvasInfo as CanvasInfoDomain

fun CanvasInfoDomain.toDataLayer(): CanvasInfo {
    return CanvasInfo(
        id = id,
        name = name
    )
}

fun CanvasInfo.toDomainLayer(): CanvasInfoDomain {
    return CanvasInfoDomain(
        id = id,
        name = name
    )
}