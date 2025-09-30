package com.example.photozhab.presentation.screens.editor

/**
 * Состояния диалогов редактора.
 */
sealed class EditorScreensDialog {

    /** Нет активного диалога. */
    data object None : EditorScreensDialog()

    /** Диалог предупреждения об удалении всех фигур. */
    data object Warning : EditorScreensDialog()

    /** Диалог выбора сохранённых проектов. */
    data object SavedProjects : EditorScreensDialog()

    /** Диалог сохранения нового проекта. */
    data object ProjectSaver : EditorScreensDialog()

    /** Диалог подтверждения удаления конкретного проекта. */
    data object DeleteSavedProject : EditorScreensDialog()
}