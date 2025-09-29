package com.example.photozhab.presentation.screens.editor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toColorLong
import com.example.photozhab.presentation.model.CanvasInfo
import com.example.photozhab.presentation.model.EditorButton
import com.example.photozhab.presentation.model.Figure
import com.example.photozhab.presentation.model.LoadingState
import com.example.photozhab.presentation.model.UiMessage

data class EditorScreenUiState(
    val figures: List<Figure> = emptyList(),
    val circleColor: Long = Color.Green.toColorLong(),
    val squareColor: Long = Color.Blue.toColorLong(),
    val brushColor: Long = Color.Red.toColorLong(),
    val triangleColor: Long = Color.Yellow.toColorLong(),
    val polygonColor: Long = Color.Magenta.toColorLong(),
    val backgroundColor: Long = Color.Black.toColorLong(),
    val lineColor: Long = Color.Green.toColorLong(),

    val brushWidth: Float = 5f,
    val polygonVertices: Int = 5,
    val lineWidth: Float = 5f,
    val savedProjectIdToDelete: Int = 0,

    val projectNameValue: String = "",
    val savedProjects: List<CanvasInfo> = emptyList(),

    val isBrushChosen: Boolean = false,
    val isPanelExpanded: Boolean = false,
    val currentEditorButton: EditorButton? = null,

    val isShowWarningDialog: Boolean = false,
    val isShowSavedProjectsDialog: Boolean = false,
    val isShowProjectSaverDialog: Boolean = false,
    val isShowDeleteSavedProject: Boolean = false,

    val loadingState: LoadingState = LoadingState.Success,
    val uiMessage: UiMessage? = null,
)