package com.example.photozhab.presentation.model

import androidx.annotation.DrawableRes

/**
 * Настройки кнопки редактора.
 *
 * @param icon Ресурс иконки кнопки.
 * @param type Тип кнопки.
 * @param onClick Действие при нажатии.
 */
data class EditorButtonSettings(
    @DrawableRes val icon: Int,
    val type: EditorButton,
    val onClick: () -> Unit,
)