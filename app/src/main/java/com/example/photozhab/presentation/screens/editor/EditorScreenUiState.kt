package com.example.photozhab.presentation.screens.editor

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import com.example.photozhab.presentation.model.EditorButton
import com.example.photozhab.presentation.model.figures.Figure

data class EditorScreenUiState(
    val figures: SnapshotStateList<Figure> = mutableStateListOf(),
    val circleColor: Color = Color.Green,
    val squareColor: Color = Color.Blue,
    val brushColor: Color = Color.Red,
    val triangleColor: Color = Color.Yellow,
    val polygonColor: Color = Color.Magenta,
    val backgroundColor: Color = Color.Black,
    val lineColor: Color = Color.Green,

    val brushWidth: Float = 5f,
    val polygonVertices: Int = 5,
    val lineWidth: Float = 5f,

    val isBrushChosen: Boolean = false,
    val isPanelExpanded: Boolean = false,
    val currentEditorButton: EditorButton? = null,
    val isShowWarningDialog: Boolean = false,
)