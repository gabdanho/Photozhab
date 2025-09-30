package com.example.photozhab.presentation.model

/**
 * Функции управления инструментами редактора.
 *
 * @param onSaveToGalleryClick Сохранить текущий холст в галерею.
 * @param onSavesClick Открыть сохранённые проекты.
 * @param onPrevStateClick Отменить последнее действие.
 * @param onForwardStateClick Повторить отменённое действие.
 * @param onDeleteAllClick Удалить все объекты с холста.
 * @param changeCurrentEditorButton Сменить активную кнопку редактора.
 * @param changeIsPanelExpanded Изменить состояние панели инструментов (развернута/свернута).
 * @param onPanelClick Действие при клике на панель инструментов.
 * @param onButtonClick Действие при клике на кнопку редактора.
 * @param onLongPressButton Действие при долгом нажатии на кнопку редактора.
 */
data class ToolsStateFunctions(
    val onSaveToGalleryClick: () -> Unit,
    val onSavesClick: () -> Unit,
    val onPrevStateClick: () -> Unit,
    val onForwardStateClick: () -> Unit,
    val onDeleteAllClick: () -> Unit,
    val changeCurrentEditorButton: (EditorButton?) -> Unit,
    val changeIsPanelExpanded: (Boolean) -> Unit,
    val onPanelClick: () -> Unit,
    val onButtonClick: (EditorButtonSettings) -> Unit,
    val onLongPressButton: (EditorButtonSettings) -> Unit,
)