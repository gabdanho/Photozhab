package com.example.photozhab.presentation.model.figures

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

data class Brush(
    val color: Color,
    val brushWidth: Float,
    var path: Path = Path(),
) : Figure {

    @Composable
    override fun draw() {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = path,
                color = color,
                style = Stroke(brushWidth, cap = StrokeCap.Round)
            )
        }
    }
}