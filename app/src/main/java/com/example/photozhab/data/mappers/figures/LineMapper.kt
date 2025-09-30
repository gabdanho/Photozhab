package com.example.photozhab.data.mappers.figures

import com.example.photozhab.data.local.model.Offset
import com.example.photozhab.data.local.model.Figure.Line
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.Figure.Line as LineDomain

/**
 * [LineDomain] -> [Line]
 *
 * @receiver [LineDomain].
 * @return [Line].
 */
fun LineDomain.toDataLayer(): Line {
    return Line(
        color = color,
        offset = Offset(offset.x, offset.y),
        angle = angle,
        lineWidth = lineWidth,
        widthDp = widthDp
    )
}

/**
 * [Line] -> [LineDomain]
 *
 * @receiver [Line].
 * @return [LineDomain].
 */
fun Line.toDomainLayer(): LineDomain {
    return LineDomain(
        color = color,
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        lineWidth = lineWidth,
        widthDp = widthDp
    )
}