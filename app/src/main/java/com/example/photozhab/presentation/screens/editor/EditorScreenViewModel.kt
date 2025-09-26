package com.example.photozhab.presentation.screens.editor

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.photozhab.presentation.model.figures.EditorButton
import com.example.photozhab.presentation.model.figures.Figure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditorScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EditorScreenUiState())
    val uiState: StateFlow<EditorScreenUiState> = _uiState.asStateFlow()

    private val deletedFigures = mutableListOf<Figure>()

    fun addFigure(figure: Figure) {
        _uiState.value.figures.add(figure)
        deletedFigures.clear()
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun prevState() {
        if (_uiState.value.figures.isNotEmpty()) {
            val prevFigure = _uiState.value.figures.removeLast()
            deletedFigures.add(prevFigure)
        }
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun forwardState() {
        if (deletedFigures.isNotEmpty()) {
            _uiState.value.figures.add(deletedFigures.removeLast())
        }
    }

    fun deleteAllFigures() {
        deletedFigures.clear()
        _uiState.value.figures.clear()
    }

    fun changeIsBrushChosen(value: Boolean) = _uiState.update { it.copy(isBrushChosen = value) }
    fun changeIsPanelExpanded(value: Boolean) = _uiState.update { it.copy(isPanelExpanded = value) }
    fun changeCurrentEditorButton(editorButton: EditorButton?) =
        _uiState.update { it.copy(currentEditorButton = editorButton) }

    // SETTING FIGURE PARAMS
    ///////////////////////////////////////////////////////////////////////////

    fun changeCircleColor(color: Color) = _uiState.update { it.copy(circleColor = color) }
    fun changeSquareColor(color: Color) = _uiState.update { it.copy(squareColor = color) }
    fun changeBrushColor(color: Color) = _uiState.update { it.copy(brushColor = color) }
    fun changeTriangleColor(color: Color) = _uiState.update { it.copy(triangleColor = color) }
    fun changePolygonColor(color: Color) = _uiState.update { it.copy(polygonColor = color) }
    fun changeLineColor(color: Color) = _uiState.update { it.copy(lineColor = color) }
    fun changeBackgroundColor(color: Color) = _uiState.update { it.copy(backgroundColor = color) }
    fun changeBrushWidth(width: Float) = _uiState.update { it.copy(brushWidth = width) }
    fun changePolygonVertices(vertices: Int) = _uiState.update { it.copy(polygonVertices = vertices) }
    fun changeLineWidth(width: Float) = _uiState.update { it.copy(lineWidth = width) }

    ///////////////////////////////////////////////////////////////////////////
}
