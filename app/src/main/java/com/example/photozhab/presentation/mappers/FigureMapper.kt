package com.example.photozhab.presentation.mappers

import com.example.photozhab.domain.model.canvas.Figure as FigureDomain
import com.example.photozhab.presentation.model.Figure

fun FigureDomain.toPresentationLayer(): Figure {
    return when (this) {
        is FigureDomain.Brush -> this.toPresentationLayer()
        is FigureDomain.Circle -> this.toPresentationLayer()
        is FigureDomain.Line -> this.toPresentationLayer()
        is FigureDomain.Polygon -> this.toPresentationLayer()
        is FigureDomain.Square -> this.toPresentationLayer()
        is FigureDomain.Triangle -> this.toPresentationLayer()
    }
}

fun Figure.toDomainLayer(): FigureDomain {
    return when (this) {
        is Figure.Brush -> this.toDomainLayer()
        is Figure.Circle -> this.toDomainLayer()
        is Figure.Line -> this.toDomainLayer()
        is Figure.Polygon -> this.toDomainLayer()
        is Figure.Square -> this.toDomainLayer()
        is Figure.Triangle -> this.toDomainLayer()
    }
}