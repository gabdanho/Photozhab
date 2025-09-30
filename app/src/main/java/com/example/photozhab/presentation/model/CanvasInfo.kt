package com.example.photozhab.presentation.model

/**
 * Информация о холсте без содержимого фигур.
 *
 * @param id Идентификатор холста.
 * @param name Имя холста.
 */
data class CanvasInfo(
    val id: Int = 0,
    val name: String = "",
)