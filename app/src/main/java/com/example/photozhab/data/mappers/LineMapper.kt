package com.example.photozhab.data.mappers

import com.example.photozhab.data.local.model.Offset
import com.example.photozhab.data.local.model.Figure.Line
import com.example.photozhab.domain.model.canvas.Offset as OffsetDomain
import com.example.photozhab.domain.model.canvas.Figure.Line as LineDomain

fun LineDomain.toDataLayer(): Line {
    return Line(
        color = color,
        offset = Offset(offset.x, offset.y),
        angle = angle,
        lineWidth = lineWidth,
        widthDp = widthDp
    )
}

fun Line.toDomainLayer(): LineDomain {
    return LineDomain(
        color = color,
        offset = OffsetDomain(offset.x, offset.y),
        angle = angle,
        lineWidth = lineWidth,
        widthDp = widthDp
    )
}