package com.example.photozhab.presentation.model

/**
 * Состояние загрузки для UI.
 */
sealed class LoadingState {

    /** Процесс загрузки данных */
    data object Loading : LoadingState()

    /** Данные успешно загружены */
    data object Success : LoadingState()

    /** Ошибка при загрузке */
    data object Error : LoadingState()
}