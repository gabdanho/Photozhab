package com.example.photozhab.presentation.mappers

import com.example.photozhab.presentation.model.CanvasInfo
import com.example.photozhab.domain.model.canvas.CanvasInfo as CanvasInfoDomain

/**
 * [CanvasInfoDomain] -> [CanvasInfo]
 *
 * @receiver [CanvasInfoDomain].
 * @return [CanvasInfo].
 */
fun CanvasInfoDomain.toPresentationLayer(): CanvasInfo {
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