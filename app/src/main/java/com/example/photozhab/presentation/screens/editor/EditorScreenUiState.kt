package com.example.photozhab.presentation.screens.editor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toColorLong
import com.example.photozhab.presentation.model.CanvasInfo
import com.example.photozhab.presentation.model.EditorButton
import com.example.photozhab.presentation.model.Figure
import com.example.photozhab.presentation.model.LoadingState
import com.example.photozhab.presentation.model.UiMessage

/**
 * UI состояние экрана редактора.
 *
 * @property figures Список текущих фигур на холсте.
 * @property circleColor Цвет круга.
 * @property squareColor Цвет квадрата.
 * @property brushColor Цвет кисти.
 * @property triangleColor Цвет треугольника.
 * @property polygonColor Цвет многоугольника.
 * @property backgroundColor Цвет фона холста.
 * @property lineColor Цвет линии.
 * @property brushWidth Толщина кисти.
 * @property polygonVertices Количество вершин многоугольника.
 * @property lineWidth Толщина линии.
 * @property savedProjectIdToDelete ID проекта для удаления.
 * @property projectNameValue Имя для сохраняемого проекта.
 * @property savedProjects Список сохранённых проектов.
 * @property isBrushChosen Выбрана ли кисть.
 * @property isPanelExpanded Открыта ли панель настроек фигуры
 * @property currentEditorButton Текущая выбранная кнопка редактора.
 * @property dialog Текущее состояние диалога.
 * @property loadingState Состояние загрузки данных.
 * @property uiMessage Сообщение UI.
 */
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

    val dialog: EditorScreensDialog = EditorScreensDialog.None,

    val loadingState: LoadingState = LoadingState.Success,
    val uiMessage: UiMessage? = null,
)