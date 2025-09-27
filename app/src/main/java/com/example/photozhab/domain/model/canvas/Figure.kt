package com.example.photozhab.domain.model.canvas

sealed class Figure {

    data class Brush(
        val color: Long = 0L,
        val brushWidth: Float = 0f,
        var path: List<PathPoints> = emptyList(),
    ) : Figure()

    data class Circle(
        val color: Long = 0L,
        var offset: Offset = Offset(),
        var angle: Float = 0f,
        var scale: Float = 1f,
    ) : Figure()

    data class Line(
        val color: Long = 0L,
        val lineWidth: Float = 0f,
        var offset: Offset = Offset(),
        var angle: Float = 0f,
        var widthDp: Int = 0,
    ) : Figure()

    data class Polygon(
        val color: Long = 0L,
        val vertices: Int = 0,
        var offset: Offset = Offset(),
        var angle: Float = 0f,
        var scale: Float = 0f,
    ) : Figure()

    data class Square(
        val color: Long = 0L,
        var offset: Offset = Offset(),
        var angle: Float = 0f,
        var scale: Float = 0f,
    ) : Figure()


    data class Triangle(
        val color: Long = 0L,
        var offset: Offset = Offset(),
        var angle: Float = 0f,
        var scale: Float = 1f,
    ) : Figure()
}