package com.example.photozhab.presentation.model

import androidx.compose.ui.graphics.Color

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
