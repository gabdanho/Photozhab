package com.example.photozhab.presentation.mappers.resources

import com.example.photozhab.R
import com.example.photozhab.presentation.model.StringResNamePresentation

/**
 * Реализация маппера, которая сопоставляет все возможные
 * идентификаторы из presentation слоя с конкретными ресурсами строк в R.string.
 * Используется для отображения локализованных текстов в UI.
 */
class StringToResourceIdMapperImpl : StringToResourceIdMapper {
    private val resourceMapPresentation = mapOf(
        StringResNamePresentation.SUCCESS_LOAD_PROJECT to R.string.text_load_project,
        StringResNamePresentation.SUCCESS_SAVE_PROJECT to R.string.text_save_project,
        StringResNamePresentation.SUCCESS_LOAD_IN_GALLERY to R.string.text_load_in_gallery,
        StringResNamePresentation.SUCCESS_DELETE_PROJECT to R.string.text_delete_project,

        StringResNamePresentation.ERROR_LOAD_PROJECT to R.string.error_load_project,
        StringResNamePresentation.ERROR_SAVE_PROJECT to R.string.error_save_project,
        StringResNamePresentation.ERROR_LOAD_IN_GALLERY to R.string.error_load_project,
        StringResNamePresentation.ERROR_DELETE_PROJECT to R.string.error_delete_project,
    )

    override fun map(resId: StringResNamePresentation): Int {
        return resourceMapPresentation[resId]
            ?: throw IllegalArgumentException("CANT_MAP_THIS_ID_$resId")
    }
}