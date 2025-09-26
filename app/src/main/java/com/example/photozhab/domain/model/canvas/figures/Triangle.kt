package com.example.photozhab.domain.model.canvas.figures

import com.example.photozhab.domain.model.canvas.Offset

data class Triangle(
    val color: Long = 0L,
    var offset: Offset = Offset(),
    var angle: Float = 0f,
    var scale: Float = 1f,
)
