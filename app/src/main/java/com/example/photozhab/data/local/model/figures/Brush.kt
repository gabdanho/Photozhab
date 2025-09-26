package com.example.photozhab.data.local.model.figures

import com.example.photozhab.data.local.model.PointF
import kotlinx.serialization.Serializable

@Serializable
data class Brush(
    val color: Long = 0L,
    val brushWidth: Float = 0f,
    var points: List<PointF> = emptyList(),
)