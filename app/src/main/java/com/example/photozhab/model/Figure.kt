package com.example.photozhab.model

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import kotlin.math.roundToInt
import kotlin.math.sqrt

interface Figure {
    @Composable
    fun draw()
}

data class Circle(
    var offset: Offset = Offset.Zero,
    var angle: Float = 0f,
    var scale: Float = 1f
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
            drawCircle(
                radius = 200.dp.toPx() / 2,
                color = Color.Green
            )
        }
    }

    private fun saveParameters(
        localOffset: Offset,
        localAngle: Float,
        localScale: Float
    ) {
        offset = localOffset
        angle = localAngle
        scale = localScale
    }

    override fun toString(): String {
        return "Circle()"
    }
}

data class Square(
    var offset: Offset = Offset.Zero,
    var angle: Float = 0f,
    var scale: Float = 1f
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
                        onGesture = { _, pan, zoom, rotation ->
                            localOffset += pan
                            localAngle += rotation
                            localScale *= zoom

                            saveParameters(localOffset, localAngle, localScale)
                        }
                    )
                }
        ) {
            drawRect(
                color = Color.Blue,
                size = this.size
            )
        }
    }

    private fun saveParameters(
        localOffset: Offset,
        localAngle: Float,
        localScale: Float
    ) {
        offset = localOffset
        angle = localAngle
        scale = localScale
    }

    override fun toString(): String {
        return "Square()"
    }
}

data class Triangle(
    var offset: Offset = Offset.Zero,
    var angle: Float = 0f,
    var scale: Float = 1f
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
            drawPath(path, Color.Red)
        }
    }

    private fun saveParameters(
        localOffset: Offset,
        localAngle: Float,
        localScale: Float
    ) {
        offset = localOffset
        angle = localAngle
        scale = localScale
    }

    override fun toString(): String {
        return "Triangle()"
    }
}

data class Polygon(
    var offset: Offset = Offset.Zero,
    var angle: Float = 0f,
    var scale: Float = 1f
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
                numVertices = 5, // позволить пользователю выбрать n-ое кол-во вершин
                radius = size.minDimension / 2,
                centerX = size.width / 2,
                centerY = size.width / 2
            )
            val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

            drawPath(roundedPolygonPath, Color.Yellow)
        }
    }

    private fun saveParameters(
        localOffset: Offset,
        localAngle: Float,
        localScale: Float
    ) {
        offset = localOffset
        angle = localAngle
        scale = localScale
    }

    override fun toString(): String {
        return "Polygon()"
    }
}

data class Line(
    var offset: Offset = Offset.Zero,
    var angle: Float = 0f,
    var widthDp: Dp = 200.dp
) : Figure {
    @Composable
    override fun draw() {
        val density = LocalDensity.current

        var localOffset by remember { mutableStateOf(offset) }
        var localAngle by remember { mutableStateOf(angle) }
        var localWidthDp by remember { mutableStateOf(widthDp) }
        var widthPx by remember { mutableStateOf(density.run { widthDp.toPx() }) }

        Canvas(
            modifier = Modifier
                .size(width = localWidthDp, height = 10.dp)
                .rotate(localAngle)
                .offset { IntOffset(localOffset.x.roundToInt(), localOffset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true,
                        onGesture = { centroid, pan, zoom, rotation ->
                            localOffset += pan
                            localAngle += rotation

                            widthPx *= zoom
                            localWidthDp = density.run { widthPx.toDp() }

                            saveParameters(localOffset, localAngle, localWidthDp)
                        }
                    )
                }
        ) {
            drawLine(
                start = Offset(0f, size.height / 2),
                end = Offset(widthPx, size.height / 2),
                color = Color.Green,
                strokeWidth = 10f
            )
        }
    }

    private fun saveParameters(
        localOffset: Offset,
        localAngle: Float,
        localWidthDp: Dp
    ) {
        offset = localOffset
        angle = localAngle
        widthDp = localWidthDp
    }

    override fun toString(): String {
        return "Line()"
    }
}

data class Brush(var path: Path = Path()) : Figure {
    @Composable
    override fun draw() {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = path,
                color = Color.Blue,
                style = Stroke(5f, cap = StrokeCap.Round)
            )
        }
    }
}