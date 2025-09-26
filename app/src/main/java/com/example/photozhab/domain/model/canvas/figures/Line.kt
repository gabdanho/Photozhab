package com.example.photozhab.domain.model.canvas.figures

import com.example.photozhab.domain.model.canvas.Offset

data class Line(
    val color: Long = 0L,
    val lineWidth: Float = 0f,
    var offset: Offset = Offset(),
    var angle: Float = 0f,
    var widthDp: Int = 0,
)
