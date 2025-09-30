package com.example.photozhab.presentation.screens.editor

sealed class EditorScreensDialog {

    data object None : EditorScreensDialog()

    data object Warning : EditorScreensDialog()

    data object SavedProjects : EditorScreensDialog()

    data object ProjectSaver : EditorScreensDialog()

    data object DeleteSavedProject : EditorScreensDialog()
}