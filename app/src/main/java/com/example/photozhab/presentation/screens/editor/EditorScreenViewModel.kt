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
import com.example.photozhab.presentation.model.LoadingState
import com.example.photozhab.presentation.model.StringResNamePresentation
import com.example.photozhab.presentation.model.UiMessage
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
        _uiState.update { it.copy(backgroundColor = DEFAULT_BACKGROUND_COLOR) }
    }

    fun changeProjectNameValue(value: String) =
        _uiState.update { it.copy(projectNameValue = value) }

    fun changeSavedProjectIdToDelete(id: Int) =
        _uiState.update { it.copy(savedProjectIdToDelete = id) }

    fun changeDialog(dialog: EditorScreensDialog) =
        _uiState.update { it.copy(dialog = dialog) }

    fun changeIsBrushChosen(value: Boolean) =
        _uiState.update { it.copy(isBrushChosen = value) }

    fun changeIsPanelExpanded(value: Boolean) =
        _uiState.update { it.copy(isPanelExpanded = value) }

    fun changeCurrentEditorButton(editorButton: EditorButton?) =
        _uiState.update { it.copy(currentEditorButton = editorButton) }

    // SETTING FIGURE PARAMS
    ///////////////////////////////////////////////////////////////////////////

    fun changeCircleColor(colorLong: Long) = _uiState.update { it.copy(circleColor = colorLong) }
    fun changeSquareColor(colorLong: Long) = _uiState.update { it.copy(squareColor = colorLong) }
    fun changeBrushColor(colorLong: Long) = _uiState.update { it.copy(brushColor = colorLong) }
    fun changeTriangleColor(colorLong: Long) =
        _uiState.update { it.copy(triangleColor = colorLong) }

    fun changePolygonColor(colorLong: Long) = _uiState.update { it.copy(polygonColor = colorLong) }
    fun changeLineColor(colorLong: Long) = _uiState.update { it.copy(lineColor = colorLong) }
    fun changeBackgroundColor(colorLong: Long) =
        _uiState.update { it.copy(backgroundColor = colorLong) }

    fun changeBrushWidth(width: Float) = _uiState.update { it.copy(brushWidth = width) }
    fun changePolygonVertices(vertices: Int) =
        _uiState.update { it.copy(polygonVertices = vertices) }

    fun changeLineWidth(width: Float) = _uiState.update { it.copy(lineWidth = width) }

    ///////////////////////////////////////////////////////////////////////////

    fun saveProject() {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingState = LoadingState.Loading) }
            try {
                val newCanvas = CanvasSave(
                    name = _uiState.value.projectNameValue,
                    background = _uiState.value.backgroundColor,
                    figures = _uiState.value.figures
                ).toDomainLayer()

                canvasRepository.saveCanvas(
                    canvas = newCanvas
                )
                deleteAllFiguresAndClearBackground()
                getSavedProjects()
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Success,
                        uiMessage = UiMessage(textResName = StringResNamePresentation.SUCCESS_SAVE_PROJECT)
                    )
                }
            } catch (_: Exception) {
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error,
                        uiMessage = UiMessage(textResName = StringResNamePresentation.ERROR_SAVE_PROJECT)
                    )
                }
            }
        }
    }

    fun deleteProject() {
        _uiState.update { it.copy(loadingState = LoadingState.Loading) }
        try {
            viewModelScope.launch {
                val id = _uiState.value.savedProjectIdToDelete
                canvasRepository.deleteCanvas(id)
                _uiState.update {
                    it.copy(
                        savedProjects = it.savedProjects.filterNot { project -> project.id == id },
                        loadingState = LoadingState.Success,
                        uiMessage = UiMessage(textResName = StringResNamePresentation.SUCCESS_DELETE_PROJECT)
                    )
                }
            }
        } catch (_: Exception) {
            _uiState.update {
                it.copy(
                    loadingState = LoadingState.Error,
                    uiMessage = UiMessage(textResName = StringResNamePresentation.ERROR_DELETE_PROJECT)
                )
            }
        }
    }

    fun getProjectById(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingState = LoadingState.Loading) }
            try {
                canvasRepository.getCanvasById(id)?.let { project ->
                    val canvas = project.toPresentationLayer()
                    _uiState.update {
                        it.copy(
                            figures = canvas.figures.toMutableStateList(),
                            backgroundColor = canvas.background
                        )
                    }
                }
                deletedFigures.clear()
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Success,
                        uiMessage = UiMessage(textResName = StringResNamePresentation.SUCCESS_LOAD_PROJECT)
                    )
                }

            } catch (_: Exception) {
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error,
                        uiMessage = UiMessage(textResName = StringResNamePresentation.ERROR_LOAD_PROJECT)
                    )
                }
            }
        }
    }

    fun saveTempCanvas() {
        runBlocking {
            val state = _uiState.value

            val currentCanvas = CanvasSave(
                figures = state.figures,
                background = state.backgroundColor
            ).toDomainLayer()
            canvasRepository.saveTempCanvas(currentCanvas)
        }
    }

    fun saveToGallery(bitmap: Bitmap) {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingState = LoadingState.Loading) }
            try {
                galleryRepository.saveProjectInGallery(bitmap = bitmap)
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Success,
                        uiMessage = UiMessage(textResName = StringResNamePresentation.SUCCESS_LOAD_IN_GALLERY)
                    )
                }
            } catch (_: Exception) {
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error,
                        uiMessage = UiMessage(textResName = StringResNamePresentation.ERROR_LOAD_IN_GALLERY)
                    )
                }
            }
        }
    }

    fun clearMessage() {
        _uiState.update { it.copy(uiMessage = null) }
    }

    private fun getSavedProjects() {
        viewModelScope.launch {
            val canvases = canvasRepository.getCanvases().map { it.toPresentationLayer() }
            _uiState.update { it.copy(savedProjects = canvases) }
        }
    }

    private fun getTempCanvas() {
        viewModelScope.launch {
            canvasRepository.getTempCanvas()?.let { temp ->
                val canvas = temp.toPresentationLayer()
                _uiState.update {
                    it.copy(
                        figures = canvas.figures.toMutableStateList(),
                        backgroundColor = canvas.background
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