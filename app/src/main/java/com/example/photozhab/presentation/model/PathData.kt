package com.example.photozhab.presentation.model

import androidx.compose.ui.graphics.Path

/**
 * Сериализуемый путь для кисти.
 *
 * @param pathPointsList Список точек пути.
 */
data class PathData(
    val pathPointsList: List<PathPoints> = emptyList(),
) {

    /** Преобразует PathData в Compose Path. */
    fun toPath(): Path {
        val path = Path()
        if (pathPointsList.isNotEmpty()) {
            for (i in 0 until pathPointsList.size) {
                path.moveTo(pathPointsList[i].moveToX, pathPointsList[i].moveToY)
                path.lineTo(pathPointsList[i].lineToX, pathPointsList[i].lineToY)
            }
        }
        return path
    }
}