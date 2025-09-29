package com.example.photozhab.presentation.screens.editor.locals

import androidx.compose.runtime.compositionLocalOf
import com.example.photozhab.presentation.model.EditorButtonsProperties
import com.example.photozhab.presentation.model.ToolsStateFunctions

val LocalToolsStateFunctions = compositionLocalOf<ToolsStateFunctions> {
    error("ToolsStateFunctions must be provide")
}

val LocalEditorButtonsProperties = compositionLocalOf<EditorButtonsProperties> {
    error("EditButtonProperties must be provide")
}