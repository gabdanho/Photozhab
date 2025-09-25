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
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import kotlin.math.roundToInt

data class Polygon(
    val color: Color,
    val vertices: Int,
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
            val roundedPolygon = RoundedPolygon(
                numVertices = vertices, // позволить пользователю выбрать n-ое кол-во вершин
                radius = size.minDimension / 2,
                centerX = size.width / 2,
                centerY = size.width / 2
            )
            val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

            drawPath(roundedPolygonPath, color)
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