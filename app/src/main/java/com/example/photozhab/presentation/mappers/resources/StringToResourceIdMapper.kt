package com.example.photozhab.presentation.mappers.resources

import androidx.annotation.StringRes
import com.example.photozhab.presentation.model.StringResNamePresentation

/**
 * Интерфейс для преобразования идентификаторов строк (StringResNamePresentation)
 * в реальные ресурсы строк Android (@StringRes).
 */
interface StringToResourceIdMapper {

    @StringRes
    fun map(resId: StringResNamePresentation): Int
}