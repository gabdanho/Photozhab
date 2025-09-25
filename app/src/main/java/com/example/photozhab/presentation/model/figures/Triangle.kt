package com.example.photozhab.presentation.model.figures

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlin.math.sqrt

data class Triangle(
    val color: Color,
    var offset: Offset = Offset.Zero,
    var angle: Float = 0f,
    var scale: Float = 1f,
) : Figure {

    @Composable
    override fun draw() {
        var localOffset by remember { mutableStateOf(offset) }
        var localAngle by remember { mutableStateOf(angle) }
        var localScale by remember { mutableStateOf(scale) }

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .rotate(localAngle)
                .scale(localScale)
                .offset { IntOffset(localOffset.x.roundToInt(), localOffset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true,
                        onGesture = { centroid, pan, zoom, rotation ->
                            localOffset += pan
                            localAngle += rotation
                            localScale *= zoom

                            saveParameters(localOffset, localAngle, localScale)
                        }
                    )
                }
        ) {
            val path = Path().apply {
                val triangleSide = size.width
                val height = triangleSide * (sqrt(3.0) / 2.0).toFloat()
                moveTo(triangleSide / 2f, 0f)
                lineTo(0f, height)
                lineTo(triangleSide, height)
                close()
            }
            drawPath(path, color)
        }
    }

    private fun saveParameters(
        localOffset: Offset,
        localAngle: Float,
        localScale: Float,
    ) {
        offset = localOffset
        angle = localAngle
        scale = localScale
    }
}