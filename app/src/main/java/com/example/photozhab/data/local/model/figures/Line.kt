package com.example.photozhab.data.local.model.figures

import com.example.photozhab.data.local.model.Offset
import kotlinx.serialization.Serializable

@Serializable
data class Line(
    val color: Long = 0L,
    val lineWidth: Float = 0f,
    var offset: Offset = Offset(),
    var angle: Float = 0f,
    var widthDp: Int = 0,
)
