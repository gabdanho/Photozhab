package com.example.photozhab.domain.model.canvas.figures

import com.example.photozhab.domain.model.canvas.Offset

data class Polygon(
    val color: Long = 0L,
    val vertices: Int = 0,
    var offset: Offset = Offset(),
    var angle: Float = 0f,
    var scale: Float = 0f,
)