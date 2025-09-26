package com.example.photozhab.presentation.model

import androidx.compose.ui.graphics.Path

data class PathData(
    val points: List<PointF> = emptyList(),
) {
    fun toPath(): Path {
        val path = Path()
        if (points.isNotEmpty()) {
            path.moveTo(points.first().x, points.first().y)
            for (i in 1 until points.size) {
                path.lineTo(points[i].x, points[i].y)
            }
        }
        return path
    }
}