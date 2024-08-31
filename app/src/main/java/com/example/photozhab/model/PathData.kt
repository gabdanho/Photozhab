package com.example.photozhab.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

data class PathData(
    val path: Path = Path(),
    val color: Color = Color.Blue,
    val width: Float = 5f
)