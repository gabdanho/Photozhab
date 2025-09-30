package com.example.photozhab.data.mappers.figures

import com.example.photozhab.data.local.model.Figure
import com.example.photozhab.domain.model.canvas.Figure as FigureDomain

/**
 * [FigureDomain] -> [Figure]
 *
 * @receiver [FigureDomain].
 * @return [Figure].
 */
fun FigureDomain.toDataLayer(): Figure {
    return when (this) {
        is FigureDomain.Brush -> this.toDataLayer()
        is FigureDomain.Circle -> this.toDataLayer()
        is FigureDomain.Line -> this.toDataLayer()
        is FigureDomain.Polygon -> this.toDataLayer()
        is FigureDomain.Square -> this.toDataLayer()
        is FigureDomain.Triangle -> this.toDataLayer()
    }
}

/**
 * [Figure] -> [FigureDomain]
 *
 * @receiver [Figure].
 * @return [FigureDomain].
 */
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