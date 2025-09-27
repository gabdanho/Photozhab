package com.example.photozhab.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class PathPoints(
    val moveToX: Float = 0f,
    val moveToY: Float = 0f,
    val lineToX: Float = 0f,
    val lineToY: Float = 0f,
)
