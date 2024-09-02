package com.example.photozhab.model

import androidx.annotation.DrawableRes

enum class TypeFigureButton {
    CIRCLE, SQUARE, TRIANGLE, POLYGON, LINE, BRUSH, BACKGROUND
}

data class ButtonPanelSettings(
    val type: TypeFigureButton,
    @DrawableRes val icon: Int,
    val isEnabled: Boolean = true,
    val onClick: () -> Unit
)