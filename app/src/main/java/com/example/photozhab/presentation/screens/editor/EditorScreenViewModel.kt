package com.example.photozhab.presentation.screens.editor

import android.graphics.Bitmap
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photozhab.domain.interfaces.repository.local.CanvasRepository
import com.example.photozhab.domain.interfaces.repository.local.GalleryRepository
import com.example.photozhab.presentation.mappers.figures.toDomainLayer
import com.example.photozhab.presentation.mappers.figures.toPresentationLayer
import com.example.photozhab.presentation.mappers.toPresentationLayer
import com.example.photozhab.presentation.model.CanvasSave
import com.example.photozhab.presentation.model.EditorButton
import com.example.photozhab.presentation.model.Figure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class EditorScreenViewModel @Inject constructor(
    private val canvasRepository: CanvasRepository,
    private val galleryRepository: GalleryRepository,
) : ViewModel() {

    init {
        getTempCanvas()
        getSavedProjects()
    }

    private val _uiState = MutableStateFlow(EditorScreenUiState())
    val uiState: StateFlow<EditorScreenUiState> = _uiState.asStateFlow()

    private val deletedFigures = mutableListOf<Figure>()

    fun addFigure(figure: Figure) {
        _uiState.update { it.copy(figures = it.figures + figure) }
        deletedFigures.clear()
    }

    fun prevState() {
        val currentFigures = _uiState.value.figures
        if (currentFigures.isNotEmpty()) {
            val prevFigure = currentFigures.last()
            deletedFigures.add(prevFigure)
            _uiState.update { it.copy(figures = currentFigures.dropLast(1)) }
        }
    }

    fun forwardState() {
        if (deletedFigures.isNotEmpty()) {
            val forwardFigure = deletedFigures.removeAt(deletedFigures.lastIndex)
            _uiState.update { it.copy(figures = it.figures + forwardFigure) }
        }
    }

    fun deleteAllFiguresAndClearBackground() {
        _uiState.update { it.copy(figures = emptyList()) }
        deletedFigures.clear()
        _uiState.update { state -> state.copy(backgroundColor = DEFAULT_BACKGROUND_COLOR) }
    }

    fun changeProjectNameValue(value: String) =
        _uiState.update { it.copy(projectNameValue = value) }

    fun changeSavedProjectIdToDelete(id: Int) =
        _uiState.update { state -> state.copy(savedProjectIdToDelete = id) }

    fun changeIsShowWarningDialog(value: Boolean) =
        _uiState.update { it.copy(isShowWarningDialog = value) }

    fun changeIsShowDeleteSavedProject(value: Boolean) =
        _uiState.update { it.copy(isShowDeleteSavedProject = value) }

    fun changeIsShowSavedProjectsDialog(value: Boolean) =
        _uiState.update { it.copy(isShowSavedProjectsDialog = value) }

    fun changeIsShowProjectSaverDialog(value: Boolean) =
        _uiState.update { it.copy(isShowProjectSaverDialog = value) }

    fun changeIsBrushChosen(value: Boolean) = _uiState.update { it.copy(isBrushChosen = value) }
    fun changeIsPanelExpanded(value: Boolean) = _uiState.update { it.copy(isPanelExpanded = value) }
    fun changeCurrentEditorButton(editorButton: EditorButton?) =
        _uiState.update { it.copy(currentEditorButton = editorButton) }

    // SETTING FIGURE PARAMS
    ///////////////////////////////////////////////////////////////////////////

    fun changeCircleColor(colorLong: Long) = _uiState.update { it.copy(circleColor = colorLong) }
    fun changeSquareColor(colorLong: Long) = _uiState.update { it.copy(squareColor = colorLong) }
    fun changeBrushColor(colorLong: Long) = _uiState.update { it.copy(brushColor = colorLong) }
    fun changeTriangleColor(colorLong: Long) = _uiState.update { it.copy(triangleColor = colorLong) }
    fun changePolygonColor(colorLong: Long) = _uiState.update { it.copy(polygonColor = colorLong) }
    fun changeLineColor(colorLong: Long) = _uiState.update { it.copy(lineColor = colorLong) }
    fun changeBackgroundColor(colorLong: Long) = _uiState.update { it.copy(backgroundColor = colorLong) }
    fun changeBrushWidth(width: Float) = _uiState.update { it.copy(brushWidth = width) }
    fun changePolygonVertices(vertices: Int) =
        _uiState.update { it.copy(polygonVertices = vertices) }

    fun changeLineWidth(width: Float) = _uiState.update { it.copy(lineWidth = width) }

    ///////////////////////////////////////////////////////////////////////////

    fun saveProject() {
        viewModelScope.launch {
            val newCanvas = CanvasSave(
                name = _uiState.value.projectNameValue,
                backgroundColor = _uiState.value.backgroundColor,
                figures = _uiState.value.figures
            ).toDomainLayer()

            canvasRepository.saveCanvas(
                canvas = newCanvas
            )
            deleteAllFiguresAndClearBackground()
            getSavedProjects()
        }
    }

    fun deleteProject() {
        viewModelScope.launch {
            val id = _uiState.value.savedProjectIdToDelete
            canvasRepository.deleteCanvas(id)
            _uiState.update { state -> state.copy(savedProjects = state.savedProjects.filterNot { it.id == id }) }
        }
    }

    fun getProjectById(id: Int) {
        viewModelScope.launch {
            canvasRepository.getCanvasById(id)?.let {
                val canvas = it.toPresentationLayer()
                _uiState.update { state ->
                    state.copy(
                        figures = canvas.figures.toMutableStateList(),
                        backgroundColor = canvas.backgroundColor
                    )
                }
            }
            deletedFigures.clear()
        }
    }

    fun saveTempCanvas() {
        runBlocking {
            val state = _uiState.value

            val currentCanvas = CanvasSave(
                figures = state.figures,
                backgroundColor = state.backgroundColor
            ).toDomainLayer()
            canvasRepository.saveTempCanvas(currentCanvas)
        }
    }

    fun saveToGallery(bitmap: Bitmap) {
        viewModelScope.launch {
            galleryRepository.saveProjectInGallery(bitmap = bitmap)
        }
    }

    private fun getSavedProjects() {
        viewModelScope.launch {
            val canvases = canvasRepository.getCanvases().map { it.toPresentationLayer() }
            _uiState.update { state -> state.copy(savedProjects = canvases) }
        }
    }

    private fun getTempCanvas() {
        viewModelScope.launch {
            canvasRepository.getTempCanvas()?.let {
                val canvas = it.toPresentationLayer()
                _uiState.update { state ->
                    state.copy(
                        figures = canvas.figures.toMutableStateList(),
                        backgroundColor = canvas.backgroundColor
                    )
                }
            }
            deletedFigures.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        saveTempCanvas()
    }

    companion object {
        private const val DEFAULT_BACKGROUND_COLOR: Long = -72057594037927936L
    }
}