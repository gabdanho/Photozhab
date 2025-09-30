package com.example.photozhab.presentation.model

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

/**
 * Базовый класс для всех фигур.
 */
sealed class Figure {

    /** Отрисовка фигуры. */
    @Composable
    open fun DrawFigure() {}

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Кисть с произвольным путем.
     *
     * @param color Цвет кисти.
     * @param brushWidth Толщина кисти.
     * @param path Путь кисти.
     */
    data class Brush(
        val color: Color,
        val brushWidth: Float,
        var path: PathData,
    ) : Figure() {

        @Composable
        override fun DrawFigure() {
            Canvas(modifier = Modifier.Companion.fillMaxSize()) {
                drawPath(
                    path = path.toPath(),
                    color = color,
                    style = Stroke(brushWidth, cap = StrokeCap.Companion.Round)
                )
            }
        }
    }

    /**
     * Круг с трансформациями (смещение, поворот, масштаб).
     *
     * @param color Цвет круга.
     * @param offset Смещение круга.
     * @param angle Угол поворота.
     * @param scale Масштаб.
     */
    data class Circle(
        val color: Color,
        var offset: Offset = Offset.Companion.Zero,
        var angle: Float = 0f,
        var scale: Float = 1f,
    ) : Figure() {
        @Composable
        override fun DrawFigure() {
            var localOffset by remember { mutableStateOf(offset) }
            var localAngle by remember { mutableFloatStateOf(angle) }
            var localScale by remember { mutableFloatStateOf(scale) }

            Canvas(
                modifier = Modifier.Companion
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
                    color = color
                )
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

    /**
     * Линия с трансформациями.
     *
     * @param color Цвет линии.
     * @param lineWidth Толщина линии.
     * @param offset Смещение линии.
     * @param angle Угол поворота линии.
     * @param widthDp Длина линии.
     */
    data class Line(
        val color: Color,
        val lineWidth: Float,
        var offset: Offset = Offset.Companion.Zero,
        var angle: Float = 0f,
        var widthDp: Dp = 200.dp,
    ) : Figure() {

        @Composable
        override fun DrawFigure() {
            val density = LocalDensity.current

            var localOffset by remember { mutableStateOf(offset) }
            var localAngle by remember { mutableFloatStateOf(angle) }
            var localWidthDp by remember { mutableStateOf(widthDp) }
            var widthPx by remember { mutableFloatStateOf(density.run { widthDp.toPx() }) }

            Canvas(
                modifier = Modifier.Companion
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
                    color = color,
                    strokeWidth = lineWidth
                )
            }
        }

        private fun saveParameters(
            localOffset: Offset,
            localAngle: Float,
            localWidthDp: Dp,
        ) {
            offset = localOffset
            angle = localAngle
            widthDp = localWidthDp
        }
    }

    /**
     * Многоугольник с трансформациями.
     *
     * @param color Цвет многоугольника.
     * @param vertices Количество вершин.
     * @param offset Смещение.
     * @param angle Угол поворота.
     * @param scale Масштаб.
     */
    data class Polygon(
        val color: Color,
        val vertices: Int,
        var offset: Offset = Offset.Companion.Zero,
        var angle: Float = 0f,
        var scale: Float = 1f,
    ) : Figure() {

        @Composable
        override fun DrawFigure() {
            var localOffset by remember { mutableStateOf(offset) }
            var localAngle by remember { mutableFloatStateOf(angle) }
            var localScale by remember { mutableFloatStateOf(scale) }

            Canvas(
                modifier = Modifier.Companion
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

    /**
     * Квадрат с трансформациями.
     *
     * @param color Цвет квадрата.
     * @param offset Смещение.
     * @param angle Угол поворота.
     * @param scale Масштаб.
     */
    data class Square(
        val color: Color,
        var offset: Offset = Offset.Companion.Zero,
        var angle: Float = 0f,
        var scale: Float = 1f,
    ) : Figure() {

        @Composable
        override fun DrawFigure() {
            var localOffset by remember { mutableStateOf(offset) }
            var localAngle by remember { mutableFloatStateOf(angle) }
            var localScale by remember { mutableFloatStateOf(scale) }

            Canvas(
                modifier = Modifier.Companion
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
                    color = color,
                    size = this.size
                )
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

    /**
     * Треугольник с трансформациями.
     *
     * @param color Цвет треугольника.
     * @param offset Смещение.
     * @param angle Угол поворота.
     * @param scale Масштаб.
     */
    data class Triangle(
        val color: Color,
        var offset: Offset = Offset.Companion.Zero,
        var angle: Float = 0f,
        var scale: Float = 1f,
    ) : Figure() {

        @Composable
        override fun DrawFigure() {
            var localOffset by remember { mutableStateOf(offset) }
            var localAngle by remember { mutableFloatStateOf(angle) }
            var localScale by remember { mutableFloatStateOf(scale) }

            Canvas(
                modifier = Modifier.Companion
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
}