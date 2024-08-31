package com.example.photozhab.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.photozhab.model.Figure

class PhotozhabViewModel : ViewModel() {
    private val _figures = mutableStateListOf<Figure>()
    val figures: List<Figure> = _figures

    fun addFigure(figure: Figure) {
        _figures.add(figure)
    }
}
