package com.example.photozhab.domain.model.canvas.figures

import com.example.photozhab.domain.model.canvas.PathPoints

data class Brush(
    val color: Long = 0L,
    val brushWidth: Float = 0f,
    var path: List<PathPoints> = emptyList(),
)