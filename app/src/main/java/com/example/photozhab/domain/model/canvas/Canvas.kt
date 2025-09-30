package com.example.photozhab.domain.model.canvas

/**
 * Модель холста с фигурами и цветом фона.
 *
 * @param id Идентификатор холста.
 * @param name Имя холста.
 * @param figures Список фигур на холсте.
 * @param background Цвет фона холста в формате Long.
 */
data class Canvas(
    val id: Int = 0,
    val name: String = "",
    val figures: List<Figure> = emptyList(),
    val background: Long = 0L,
)
