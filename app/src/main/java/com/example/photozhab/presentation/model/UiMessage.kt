package com.example.photozhab.presentation.model

/**
 * Сообщение для отображения в UI (ошибки, уведомления и др.).
 *
 * @property id Уникальный идентификатор сообщения.
 * @property textResName Ресурс строки из [StringResNamePresentation].
 * @property details Дополнительная информация о сообщении.
 */
data class UiMessage(
    val id: Long = System.currentTimeMillis(),
    val textResName: StringResNamePresentation? = null,
    val details: String = "",
)
