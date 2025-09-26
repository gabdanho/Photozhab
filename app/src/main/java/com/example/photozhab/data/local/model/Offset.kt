package com.example.photozhab.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class Offset(
    val x: Float = 0f,
    val y: Float = 0f,
)
