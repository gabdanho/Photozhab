package com.example.photozhab.presentation.model

/**
 * Точки для построения пути кистью.
 *
 * @param moveToX Начальная координата X сегмента.
 * @param moveToY Начальная координата Y сегмента.
 * @param lineToX Конечная координата X сегмента.
 * @param lineToY Конечная координата Y сегмента.
 */
data class PathPoints(
    val moveToX: Float = 0f,
    val moveToY: Float = 0f,
    val lineToX: Float = 0f,
    val lineToY: Float = 0f,
)
