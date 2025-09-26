package com.example.photozhab.domain.model.canvas.figures

import com.example.photozhab.domain.model.canvas.PointF

data class Brush(
    val color: Long = 0L,
    val brushWidth: Float = 0f,
    var points: List<PointF> = emptyList(),
)