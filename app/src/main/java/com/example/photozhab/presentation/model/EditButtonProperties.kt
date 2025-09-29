package com.example.photozhab.presentation.model

import androidx.compose.ui.graphics.Color

data class EditButtonProperties(
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
    val changeCircleColor: (Color) -> Unit,
    val changeSquareColor: (Color) -> Unit,
    val changeTriangleColor: (Color) -> Unit,
    val changePolygonColor: (Color) -> Unit,
    val changeLineColor: (Color) -> Unit,
    val changePolygonVertices: (Int) -> Unit,
    val changeLineWidth: (Float) -> Unit,
    val changeBrushColor: (Color) -> Unit,
    val changeBrushWidth: (Float) -> Unit,
    val changeBackgroundColor: (Color) -> Unit,
)
