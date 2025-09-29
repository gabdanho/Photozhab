package com.example.photozhab.presentation.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val ultraSmall: Dp,
    val verySmall: Dp,
    val small: Dp,
    val medium: Dp,
    val iconButtonSize: Dp,
    val colorBox: Dp,
    val widthDialogWeight: Float,
    val fullWeight: Float,
)

val defaultDimensions = Dimensions(
    ultraSmall = 4.dp,
    verySmall = 8.dp,
    small = 12.dp,
    medium = 16.dp,
    iconButtonSize = 20.dp,
    colorBox = 40.dp,
    widthDialogWeight = 0.7f,
    fullWeight = 1f,
)