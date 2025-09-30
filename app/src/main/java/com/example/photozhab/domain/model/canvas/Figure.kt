package com.example.photozhab.domain.model.canvas

/**
 * Сущность фигуры, может быть одной из типов: Brush, Circle, Line, Polygon, Square, Triangle.
 */
sealed class Figure {

    /**
     * Кисть с набором точек.
     *
     * @param color Цвет кисти.
     * @param brushWidth Толщина кисти.
     * @param path Список точек для отрисовки.
     */
    data class Brush(
        val color: Long = 0L,
        val brushWidth: Float = 0f,
        val path: List<PathPoints> = emptyList(),
    ) : Figure()

    /**
     * Круг.
     *
     * @param color Цвет круга.
     * @param offset Смещение (позиция) круга.
     * @param angle Поворот.
     * @param scale Масштаб.
     */
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
     * @param offset Смещение линии.
     * @param angle Угол наклона.
     * @param widthDp Ширина в dp.
     */
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
     * @param color Цвет многоугольника.
     * @param vertices Количество вершин.
     * @param offset Смещение многоугольника.
     * @param angle Угол поворота.
     * @param scale Масштаб.
     */
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
     * @param angle Угол поворота.
     * @param scale Масштаб.
     */
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
     * @param angle Угол поворота.
     * @param scale Масштаб.
     */
    data class Triangle(
        val color: Long = 0L,
        val offset: Offset = Offset(),
        val angle: Float = 0f,
        val scale: Float = 1f,
    ) : Figure()
}