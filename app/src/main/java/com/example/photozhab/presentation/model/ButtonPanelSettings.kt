package com.example.photozhab.presentation.model

import androidx.annotation.DrawableRes
import com.example.photozhab.presentation.model.figures.TypeFigureButton

data class ButtonPanelSettings(
    @DrawableRes val icon: Int,
    val type: TypeFigureButton,
    val isEnabled: Boolean = true,
    val onClick: () -> Unit,
)