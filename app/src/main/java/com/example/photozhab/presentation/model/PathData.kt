package com.example.photozhab.presentation.model

import androidx.compose.ui.graphics.Path

data class PathData(
    val pathPointsList: List<PathPoints> = emptyList(),
) {
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