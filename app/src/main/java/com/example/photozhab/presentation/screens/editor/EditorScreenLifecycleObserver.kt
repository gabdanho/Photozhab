package com.example.photozhab.presentation.screens.editor

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Lifecycle observer для EditorScreen.
 *
 * Сохраняет временный холст при остановке экрана.
 *
 * @param saveCanvas Функция сохранения текущего холста.
 */
class EditorScreenLifecycleObserver(
    private val saveCanvas: () -> Unit,
) : DefaultLifecycleObserver {

    override fun onStop(owner: LifecycleOwner) {
        saveCanvas()
    }
}