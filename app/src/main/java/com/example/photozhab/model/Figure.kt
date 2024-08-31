package com.example.photozhab.model

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
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

class Circle : Figure {
    @Composable
    override fun draw() {
        var offset by remember { mutableStateOf(Offset.Zero) }
        var angle by remember { mutableFloatStateOf(0f) }
        var scale by remember { mutableFloatStateOf(1f) }

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .rotate(angle)
                .scale(scale)
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true,
                        onGesture = { centroid, pan, zoom, rotation ->
                            offset += pan
                            angle += rotation
                            scale *= zoom
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

    override fun toString(): String {
        return "Circle()"
    }
}

class Square : Figure {
    @Composable
    override fun draw() {
        var offset by remember { mutableStateOf(Offset.Zero) }
        var angle by remember { mutableFloatStateOf(0f) }
        var scale by remember { mutableFloatStateOf(1f) }

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .rotate(angle)
                .scale(scale)
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true,
                        onGesture = { centroid, pan, zoom, rotation ->
                            offset += pan
                            angle += rotation
                            scale *= zoom
                        }
                    )
                }
        ) {
            drawRect(
                color = Color.Blue,
                size = Size(200.dp.toPx(), 200f.dp.toPx())
            )
        }
    }

    override fun toString(): String {
        return "Square()"
    }
}

class Triangle : Figure {
    @Composable
    override fun draw() {
        var offset by remember { mutableStateOf(Offset.Zero) }
        var angle by remember { mutableFloatStateOf(0f) }
        var scale by remember { mutableFloatStateOf(1f) }

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .rotate(angle)
                .scale(scale)
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true,
                        onGesture = { centroid, pan, zoom, rotation ->
                            offset += pan
                            angle += rotation
                            scale *= zoom
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

    override fun toString(): String {
        return "Triangle()"
    }
}

class Polygon : Figure {
    @Composable
    override fun draw() {
        var offset by remember { mutableStateOf(Offset.Zero) }
        var angle by remember { mutableFloatStateOf(0f) }
        var scale by remember { mutableFloatStateOf(1f) }

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .rotate(angle)
                .scale(scale)
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true,
                        onGesture = { centroid, pan, zoom, rotation ->
                            offset += pan
                            angle += rotation
                            scale *= zoom
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

    override fun toString(): String {
        return "Polygon()"
    }
}

class Line : Figure {
    @Composable
    override fun draw() {
        var offset by remember { mutableStateOf(Offset.Zero) }
        var angle by remember { mutableFloatStateOf(0f) }
        val density = LocalDensity.current
        var widthDp by remember { mutableStateOf(200.dp) }
        var widthPx by remember { mutableStateOf(density.run { 200.dp.toPx() }) }

        Canvas(
            modifier = Modifier
                .size(width = widthDp, height = 10.dp)
                .rotate(angle)
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true,
                        onGesture = { centroid, pan, zoom, rotation ->
                            offset += pan
                            angle += rotation
                            widthPx *= zoom
                            widthDp = density.run { widthPx.toDp() }
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

    override fun toString(): String {
        return "Line()"
    }
}

data class Brush(
    var path: Path = Path()
) : Figure {
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