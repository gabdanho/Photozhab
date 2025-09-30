package com.example.photozhab.data.local.model

import kotlinx.serialization.Serializable

/**
 * Смещение фигуры на Canvas.
 *
 * @param x Координата X.
 * @param y Координата Y.
 */
@Serializable
data class Offset(
    val x: Float = 0f,
    val y: Float = 0f,
)
