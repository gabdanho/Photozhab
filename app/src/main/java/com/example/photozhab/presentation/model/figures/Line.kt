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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

data class Line(
    val color: Color,
    val lineWidth: Float,
    var offset: Offset = Offset.Zero,
    var angle: Float = 0f,
    var widthDp: Dp = 200.dp,
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