package com.example.photozhab.presentation.model.figures

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.photozhab.presentation.model.PathData

data class Brush(
    val color: Color,
    val brushWidth: Float,
    var pathData: PathData = PathData(),
) : Figure {

    @Composable
    override fun draw() {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = pathData.toPath(),
                color = color,
                style = Stroke(brushWidth, cap = StrokeCap.Round)
            )
        }
    }
}