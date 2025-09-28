package com.example.photozhab.presentation.screens.editor

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class EditorScreenLifecycleObserver(
    private val saveCanvas: () -> Unit,
) : DefaultLifecycleObserver {

    override fun onStop(owner: LifecycleOwner) {
        saveCanvas()
    }
}