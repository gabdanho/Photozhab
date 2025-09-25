package com.example.photozhab.presentation.screens.editor

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.photozhab.presentation.model.figures.Figure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PhotozhabUiState(
    val figures: SnapshotStateList<Figure> = mutableStateListOf(),
    var circleColor: Color = Color.Green,
    val squareColor: Color = Color.Blue,
    val brushColor: Color = Color.Red,
    val brushWidth: Float = 5f,
    val triangleColor: Color = Color.Yellow,
    val polygonColor: Color = Color.Magenta,
    val backgroundColor: Color = Color.Black,
    val polygonVertices: Int = 5,
    val lineColor: Color = Color.Green,
    val lineWidth: Float = 5f
)

class PhotozhabViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PhotozhabUiState())
    val uiState: StateFlow<PhotozhabUiState> = _uiState.asStateFlow()

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
