package com.example.photozhab.data.local.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Figure {

    @Serializable
    data class Brush(
        val color: Long = 0L,
        val brushWidth: Float = 0f,
        val path: List<PathPoints> = emptyList(),
    ) : Figure()

    @Serializable
    data class Circle(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 1f,
    ) : Figure()

    @Serializable
    data class Line(
        val color: Long = 0L,
        val lineWidth: Float = 0f,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val widthDp: Int = 0,
    ) : Figure()

    @Serializable
    data class Polygon(
        val color: Long = 0L,
        val vertices: Int = 0,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 0f,
    ) : Figure()

    @Serializable
    data class Square(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 0f,
    ) : Figure()

    @Serializable
    data class Triangle(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 1f,
    ) : Figure()
}