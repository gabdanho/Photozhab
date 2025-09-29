package com.example.photozhab.presentation.model

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