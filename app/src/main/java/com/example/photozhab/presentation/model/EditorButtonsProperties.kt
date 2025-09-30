package com.example.photozhab.presentation.model

import androidx.compose.ui.graphics.Color

/**
 * Свойства всех кнопок редактора и параметры редактирования.
 *
 * @param pressedCurrentEditorButton Текущая нажатая кнопка.
 * @param brushColor Цвет кисти.
 * @param circleColor Цвет круга.
 * @param squareColor Цвет квадрата.
 * @param triangleColor Цвет треугольника.
 * @param polygonColor Цвет многоугольника.
 * @param lineColor Цвет линии.
 * @param backgroundColor Цвет фона.
 * @param brushWidth Толщина кисти.
 * @param lineWidth Толщина линии.
 * @param polygonVertices Количество вершин многоугольника.
 * @param changeCircleColor Функция изменения цвета круга.
 * @param changeSquareColor Функция изменения цвета квадрата.
 * @param changeTriangleColor Функция изменения цвета треугольника.
 * @param changePolygonColor Функция изменения цвета многоугольника.
 * @param changeLineColor Функция изменения цвета линии.
 * @param changeBrushColor Функция изменения цвета кисти.
 * @param changeBackgroundColor Функция изменения цвета фона.
 * @param changePolygonVertices Функция изменения количества вершин многоугольника.
 * @param changeLineWidth Функция изменения толщины линии.
 * @param changeBrushWidth Функция изменения толщины кисти.
 */
data class EditorButtonsProperties(
    val pressedCurrentEditorButton: EditorButton?,
    val brushColor: Color,
    val circleColor: Color,
    val squareColor: Color,
    val triangleColor: Color,
    val polygonColor: Color,
    val lineColor: Color,
    val backgroundColor: Color,
    val brushWidth: Float,
    val lineWidth: Float,
    val polygonVertices: Int,
    val changeCircleColor: (Long) -> Unit,
    val changeSquareColor: (Long) -> Unit,
    val changeTriangleColor: (Long) -> Unit,
    val changePolygonColor: (Long) -> Unit,
    val changeLineColor: (Long) -> Unit,
    val changeBrushColor: (Long) -> Unit,
    val changeBackgroundColor: (Long) -> Unit,
    val changePolygonVertices: (Int) -> Unit,
    val changeLineWidth: (Float) -> Unit,
    val changeBrushWidth: (Float) -> Unit,
)
