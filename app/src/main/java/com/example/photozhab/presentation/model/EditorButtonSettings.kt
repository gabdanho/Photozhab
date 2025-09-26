package com.example.photozhab.presentation.model

import androidx.annotation.DrawableRes
import com.example.photozhab.presentation.model.EditorButton

data class EditorButtonSettings(
    @DrawableRes val icon: Int,
    val type: EditorButton,
    val isEnabled: Boolean = true,
    val onClick: () -> Unit,
)