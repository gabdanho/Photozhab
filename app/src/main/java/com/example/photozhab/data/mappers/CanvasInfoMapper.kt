package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.model.CanvasInfo
import com.example.photozhab.domain.model.canvas.CanvasInfo as CanvasInfoDomain

/**
 * [CanvasInfoDomain] -> [CanvasInfo]
 *
 * @receiver [CanvasInfoDomain].
 * @return [CanvasInfo].
 */
fun CanvasInfoDomain.toDataLayer(): CanvasInfo {
    return CanvasInfo(
        id = id,
        name = name
    )
}

/**
 * [CanvasInfo] -> [CanvasInfoDomain]
 *
 * @receiver [CanvasInfo].
 * @return [CanvasInfoDomain].
 */
fun CanvasInfo.toDomainLayer(): CanvasInfoDomain {
    return CanvasInfoDomain(
        id = id,
        name = name
    )
}