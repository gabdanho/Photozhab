package com.example.photozhab.domain.model.canvas

/**
 * Смещение (позиция) фигуры по осям X и Y.
 *
 * @param x Координата X.
 * @param y Координата Y.
 */
data class Offset(
    val x: Float = 0f,
    val y: Float = 0f,
)
