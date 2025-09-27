package com.example.photozhab.presentation.model

import androidx.annotation.DrawableRes

data class EditorButtonSettings(
    @DrawableRes val icon: Int,
    val type: EditorButton,
    val onClick: () -> Unit,
)