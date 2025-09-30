package com.example.photozhab.data.local.model

import kotlinx.serialization.Serializable

/**
 * Точки для рисования линии кистью.
 *
 * @param moveToX Начальная координата X.
 * @param moveToY Начальная координата Y.
 * @param lineToX Конечная координата X.
 * @param lineToY Конечная координата Y.
 */
@Serializable
data class PathPoints(
    val moveToX: Float = 0f,
    val moveToY: Float = 0f,
    val lineToX: Float = 0f,
    val lineToY: Float = 0f,
)
