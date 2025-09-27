package com.example.photozhab.data.local.model.figures

import com.example.photozhab.data.local.model.PathPoints
import kotlinx.serialization.Serializable

@Serializable
data class Brush(
    val color: Long = 0L,
    val brushWidth: Float = 0f,
    var path: List<PathPoints> = emptyList(),
)