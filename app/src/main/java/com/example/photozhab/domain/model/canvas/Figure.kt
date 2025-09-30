package com.example.photozhab.domain.model.canvas

sealed class Figure {

    data class Brush(
        val color: Long = 0L,
        val brushWidth: Float = 0f,
        val path: List<PathPoints> = emptyList(),
    ) : Figure()

    data class Circle(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 1f,
    ) : Figure()

    data class Line(
        val color: Long = 0L,
        val lineWidth: Float = 0f,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val widthDp: Int = 0,
    ) : Figure()

    data class Polygon(
        val color: Long = 0L,
        val vertices: Int = 0,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 0f,
    ) : Figure()

    data class Square(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 0f,
    ) : Figure()


    data class Triangle(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 1f,
    ) : Figure()
}