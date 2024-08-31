package com.example.photozhab.model

data class ButtonPanelSettings(
    val textName: String,
    val isEnabled: Boolean = true,
    val onClick: () -> Unit
)