package com.example.photozhab.domain.model.canvas

data class Canvas(
    val id: Int = -1,
    val name: String = "",
    val figures: List<Figure> = emptyList(),
    val backgroundColor: Long = 0L,
)
