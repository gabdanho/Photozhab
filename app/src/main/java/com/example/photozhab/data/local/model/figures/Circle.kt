package com.example.photozhab.data.local.model.figures

import com.example.photozhab.data.local.model.Offset
import kotlinx.serialization.Serializable

@Serializable
data class Circle(
    val color: Long = 0L,
    var offset: Offset = Offset(),
    var angle: Float = 0f,
    var scale: Float = 1f,
)
