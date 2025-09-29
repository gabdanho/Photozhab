package com.example.photozhab.presentation.model

data class CanvasSave(
    val id: Int = 0,
    val name: String = "",
    val figures: List<Figure> = emptyList(),
    val backgroundColor: Long = 0xFF000000,
)