package com.example.photozhab.domain.model.canvas

import com.example.photozhab.data.local.model.figures.Figure

data class Canvas(
    val id: Int = -1,
    val figures: List<Figure> = emptyList(),
    val backgroundColor: Long = 0L,
)
