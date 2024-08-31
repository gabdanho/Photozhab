package com.example.photozhab.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawContext
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.photozhab.model.Brush
import com.example.photozhab.model.ButtonPanelSettings
import com.example.photozhab.model.Circle
import com.example.photozhab.model.Figure
import com.example.photozhab.model.Line
import com.example.photozhab.model.PathData
import com.example.photozhab.model.Polygon
import com.example.photozhab.model.Square
import com.example.photozhab.model.Triangle
import kotlinx.coroutines.withContext

@Composable
fun EditorScreen(
    modifier: Modifier = Modifier
) {
    val figures = remember { mutableStateListOf<Figure>() }
    var isBrushChosen by remember { mutableStateOf(false) }

    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .weight(1f)
                .clipToBounds()
        ) {
            figures.forEach { figure ->
                figure.draw()
            }
            if (isBrushChosen) {
                DrawingCanvas(isBrushChosen, figures)
            }
        }
        FiguresButtonPanel(figures) {
            isBrushChosen = !isBrushChosen
        }
    }
}

@Composable
fun FiguresButtonPanel(
    figures: SnapshotStateList<Figure>,
    changeIsChosen: () -> Unit
) {
    val buttons = listOf(
        ButtonPanelSettings("Circle") { figures.add(Circle()) },
        ButtonPanelSettings("Square") { figures.add(Square()) },
        ButtonPanelSettings("Triangle") { figures.add(Triangle()) },
        ButtonPanelSettings("Polygon") { figures.add(Polygon()) },
        ButtonPanelSettings("Line") { figures.add(Line()) },
        ButtonPanelSettings("Brush") {
            changeIsChosen()
        }
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(buttons) { button ->
            Button(onClick = button.onClick) {
                Text(button.textName)
            }
        }
        println()
        println("START LIST")
        figures.forEach { println(it.toString()) }
        println("END LIST")
        println()
    }
}

@Composable
fun DrawingCanvas(
    isChosen: Boolean,
    figures: SnapshotStateList<Figure>
) {
    val pathData = remember { mutableStateOf(PathData()) }
    var tempPath by remember { mutableStateOf(Path()) }
    val pathList = remember { mutableStateListOf(PathData()) }
    Canvas(
        Modifier
            .fillMaxSize()
            .pointerInput(isChosen) {
                if (isChosen) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            println("Drag")
                            tempPath.moveTo(
                                change.position.x - dragAmount.x,
                                change.position.y - dragAmount.y
                            )
                            tempPath.lineTo(
                                change.position.x,
                                change.position.y
                            )

                            if (pathList.size > 0) {
                                pathList.removeLast()
                            }

                            pathList.add(
                                pathData.value.copy(
                                    path = tempPath
                                )
                            )
                        },
                        onDragStart = {
                            tempPath = Path()
                        },
                        onDragEnd = {
                            figures.add(Brush(tempPath))
                        }
                    )
                }
            }
    ) {
        pathList.forEach {
            drawPath(
                color = Color.Blue,
                path = it.path,
                style = Stroke(it.width, cap = StrokeCap.Round)
            )
        }
    }
}

@Preview
@Composable
fun EditorScreenPreview() {
    EditorScreen()
}