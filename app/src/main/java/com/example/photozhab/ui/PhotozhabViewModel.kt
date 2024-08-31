package com.example.photozhab.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.photozhab.model.Brush
import com.example.photozhab.model.Figure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class PhotozhabUiState(val figures: SnapshotStateList<Figure> = mutableStateListOf())

class PhotozhabViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PhotozhabUiState())
    val uiState: StateFlow<PhotozhabUiState> = _uiState

    private val deletedFigures = mutableListOf<Figure>()

    fun addFigure(figure: Figure) {
        _uiState.value.figures.add(figure)
        deletedFigures.clear()
    }

    fun prevState() {
        if (_uiState.value.figures.isNotEmpty()) {
            val prevFigure = _uiState.value.figures.removeLast()
            deletedFigures.add(prevFigure)
        }
    }

    fun forwardState() {
        if (deletedFigures.isNotEmpty()) {
            _uiState.value.figures.add(deletedFigures.removeLast())
        }
    }
}
