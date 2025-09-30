package com.example.photozhab.data.local.model

import kotlinx.serialization.Serializable

/**
 * Сущности фигур для рисования.
 */
@Serializable
sealed class Figure {

    /**
     * Кисть для рисования произвольных линий.
     *
     * @param color Цвет кисти.
     * @param brushWidth Толщина линии кисти.
     * @param path Список точек линии.
     */
    @Serializable
    data class Brush(
        val color: Long = 0L,
        val brushWidth: Float = 0f,
        val path: List<PathPoints> = emptyList(),
    ) : Figure()

    /**
     * Круг.
     *
     * @param color Цвет фигуры.
     * @param offset Смещение фигуры на Canvas.
     * @param angle Поворот фигуры.
     * @param scale Масштаб фигуры.
     */
    @Serializable
    data class Circle(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 1f,
    ) : Figure()

    /**
     * Линия.
     *
     * @param color Цвет линии.
     * @param lineWidth Толщина линии.
     * @param offset Смещение линии на Canvas.
     * @param angle Поворот линии.
     * @param widthDp Длина линии в dp.
     */
    @Serializable
    data class Line(
        val color: Long = 0L,
        val lineWidth: Float = 0f,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val widthDp: Int = 0,
    ) : Figure()

    /**
     * Многоугольник.
     *
     * @param color Цвет фигуры.
     * @param vertices Количество вершин.
     * @param offset Смещение фигуры.
     * @param angle Поворот фигуры.
     * @param scale Масштаб фигуры.
     */
    @Serializable
    data class Polygon(
        val color: Long = 0L,
        val vertices: Int = 0,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 0f,
    ) : Figure()

    /**
     * Квадрат.
     *
     * @param color Цвет квадрата.
     * @param offset Смещение квадрата.
     * @param angle Поворот квадрата.
     * @param scale Масштаб квадрата.
     */
    @Serializable
    data class Square(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 0f,
    ) : Figure()

    /**
     * Треугольник.
     *
     * @param color Цвет треугольника.
     * @param offset Смещение треугольника.
     * @param angle Поворот треугольника.
     * @param scale Масштаб треугольника.
     */
    @Serializable
    data class Triangle(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 1f,
    ) : Figure()
}