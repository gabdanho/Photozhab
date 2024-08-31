package com.example.photozhab.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.photozhab.model.Brush
import com.example.photozhab.model.ButtonPanelSettings
import com.example.photozhab.model.Circle
import com.example.photozhab.model.Figure
import com.example.photozhab.model.Line
import com.example.photozhab.model.PathData
import com.example.photozhab.model.Polygon
import com.example.photozhab.model.Square
import com.example.photozhab.model.Triangle
import com.example.photozhab.ui.PhotozhabViewModel

@Composable
fun EditorScreen(viewModel: PhotozhabViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
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
            uiState.figures.forEach { figure ->
                figure.draw()
            }
            if (isBrushChosen) {
                DrawingCanvas(isBrushChosen, viewModel::addFigure)
            }
        }
        ToolPanel(
            addFigure = viewModel::addFigure,
            changeIsBrushChosen = { isBrushChosen = !isBrushChosen },
            onPrevStateClick = viewModel::prevState,
            onForwardStateClick = viewModel::forwardState
        )
    }
}

@Composable
fun DrawingCanvas(
    isChosen: Boolean,
    addFigure: (Figure) -> Unit
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
                            addFigure(Brush(tempPath))
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

@Composable
fun ToolPanel(
    onPrevStateClick: () -> Unit,
    onForwardStateClick: () -> Unit,
    addFigure: (Figure) -> Unit,
    changeIsBrushChosen: () -> Unit
) {
    Column {
        StateFiguresButtonPanel(onPrevStateClick, onForwardStateClick)
        FiguresButtonPanel(addFigure) {
            changeIsBrushChosen()
        }
    }
}

@Composable
fun StateFiguresButtonPanel(
    onPrevStateClick: () -> Unit,
    onForwardStateClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onPrevStateClick
        ) {
            Icon(Icons.Default.ArrowBack, "Previous state")
        }
        IconButton(
            modifier = Modifier.padding(end = 4.dp),
            onClick = onForwardStateClick
        ) {
            Icon(Icons.Default.ArrowForward, "Forward state")
        }
    }
}

@Composable
fun FiguresButtonPanel(
    addFigure: (Figure) -> Unit,
    changeIsChosen: () -> Unit
) {
    val buttons = listOf(
        ButtonPanelSettings("Circle") { addFigure(Circle()) },
        ButtonPanelSettings("Square") { addFigure(Square()) },
        ButtonPanelSettings("Triangle") { addFigure(Triangle()) },
        ButtonPanelSettings("Polygon") { addFigure(Polygon()) },
        ButtonPanelSettings("Line") { addFigure(Line()) },
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
    }
}

@Preview
@Composable
fun EditorScreenPreview() {
    EditorScreen()
}