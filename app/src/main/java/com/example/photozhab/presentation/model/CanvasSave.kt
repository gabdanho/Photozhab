package com.example.photozhab.presentation.model

import androidx.compose.ui.graphics.Color

data class CanvasSave(
    val id: Int = 0,
    val name: String = "",
    val figures: List<Figure> = emptyList(),
    val backgroundColor: Color = Color.Unspecified,
)