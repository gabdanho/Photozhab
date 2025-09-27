package com.example.photozhab.presentation.screens.editor

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.photozhab.R
import com.example.photozhab.presentation.components.ColorPicker
import com.example.photozhab.presentation.components.DialogToDeleteData
import com.example.photozhab.presentation.components.VerticesPicker
import com.example.photozhab.presentation.components.WidthPicker
import com.example.photozhab.presentation.model.EditorButtonSettings
import com.example.photozhab.presentation.model.figures.Brush
import com.example.photozhab.presentation.model.figures.Circle
import com.example.photozhab.presentation.model.figures.Figure
import com.example.photozhab.presentation.model.figures.Line
import com.example.photozhab.presentation.model.figures.Polygon
import com.example.photozhab.presentation.model.figures.Square
import com.example.photozhab.presentation.model.figures.Triangle
import com.example.photozhab.presentation.model.EditorButton
import com.example.photozhab.presentation.model.PathData
import com.example.photozhab.presentation.model.PathPoints

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun EditorScreen(viewModel: EditorScreenViewModel = hiltViewModel<EditorScreenViewModel>()) {
    val uiState by viewModel.uiState.collectAsState()
    val buttons = listOf(
        EditorButtonSettings(
            icon = R.drawable.brush,
            type = EditorButton.BRUSH
        ) {
            viewModel.changeIsBrushChosen(value = !uiState.isBrushChosen)
        },
        EditorButtonSettings(
            icon = R.drawable.circle,
            type = EditorButton.CIRCLE
        ) {
            viewModel.addFigure(figure = Circle(color = uiState.circleColor))
        },
        EditorButtonSettings(
            icon = R.drawable.square,
            type = EditorButton.SQUARE
        ) {
            viewModel.addFigure(figure = Square(color = uiState.squareColor))
        },
        EditorButtonSettings(
            icon = R.drawable.triangle,
            type = EditorButton.TRIANGLE
        ) {
            viewModel.addFigure(figure = Triangle(color = uiState.triangleColor))
        },
        EditorButtonSettings(
            icon = R.drawable.polygon,
            type = EditorButton.POLYGON
        ) {
            viewModel.addFigure(
                figure = Polygon(
                    color = uiState.polygonColor,
                    vertices = uiState.polygonVertices
                )
            )
        },
        EditorButtonSettings(
            icon = R.drawable.line,
            type = EditorButton.LINE
        ) {
            viewModel.addFigure(
                figure = Line(
                    color = uiState.lineColor,
                    lineWidth = uiState.lineWidth
                )
            )
        },
        EditorButtonSettings(
            icon = R.drawable.background,
            type = EditorButton.BACKGROUND
        ) { }
    )

    BackHandler(enabled = uiState.isPanelExpanded) {
        viewModel.changeIsPanelExpanded(value = false)
    }

    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clipToBounds()
                .drawBehind {
                    drawRect(color = uiState.backgroundColor)
                }
        ) {
            uiState.figures.forEach { figure ->
                figure.draw()
            }
            if (uiState.isBrushChosen) {
                DrawingCanvas(
                    brushColor = uiState.brushColor,
                    brushWidth = uiState.brushWidth,
                    onPathDrawn = { viewModel.addFigure(figure = it) },
                    onDrag = {
                        viewModel.changeIsPanelExpanded(value = false)
                    }
                )
            }

            if (uiState.isPanelExpanded) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                    FigureSettingsPanel(
                        pressedCurrentEditorButton = uiState.currentEditorButton,
                        changePolygonVertices = { viewModel.changePolygonVertices(vertices = it) },
                        changeTriangleColor = { viewModel.changeTriangleColor(color = it) },
                        changeCircleColor = { viewModel.changeCircleColor(color = it) },
                        changeSquareColor = { viewModel.changeSquareColor(color = it) },
                        changePolygonColor = { viewModel.changePolygonColor(color = it) },
                        changeLineColor = { viewModel.changeLineColor(color = it) },
                        changeLineWidth = { viewModel.changeLineWidth(width = it) },
                        changeBrushWidth = { viewModel.changeBrushWidth(width = it) },
                        changeBrushColor = { viewModel.changeBrushColor(color = it) },
                        changeBackgroundColor = { viewModel.changeBackgroundColor(color = it) },
                        brushColor = uiState.brushColor,
                        circleColor = uiState.circleColor,
                        squareColor = uiState.squareColor,
                        triangleColor = uiState.triangleColor,
                        polygonColor = uiState.polygonColor,
                        backgroundColor = uiState.backgroundColor,
                        lineColor = uiState.lineColor,
                        lineWidth = uiState.lineWidth,
                        brushWidth = uiState.brushWidth,
                        polygonVertices = uiState.polygonVertices
                    )
                }
            }
        }
        ToolPanel(
            buttons = buttons,
            isBrushChosen = uiState.isBrushChosen,
            onPrevStateClick = { viewModel.prevState() },
            onForwardStateClick = { viewModel.forwardState() },
            onDeleteAllClick = { viewModel.changeIsShowWarningDialog(value = true) },
            changeCurrentEditorButton = { viewModel.changeCurrentEditorButton(editorButton = it) },
            changeIsPanelExpanded = { viewModel.changeIsPanelExpanded(value = it) }
        )
    }

    if (uiState.isShowWarningDialog) {
        DialogToDeleteData(
            onDismiss = { viewModel.changeIsShowWarningDialog(value = false) },
            onAccess = {
                viewModel.deleteAllFigures()
                viewModel.changeIsShowWarningDialog(value = false)
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
private fun DrawingCanvas(
    brushColor: Color,
    brushWidth: Float,
    onPathDrawn: (Figure) -> Unit,
    onDrag: () -> Unit = { },
) {
//    var tempPathPoints by remember { mutableStateOf(PathPoints()) }
    var currentPathPoints = remember { mutableListOf<PathPoints>() }
    val pathList = remember { mutableStateListOf(PathData()) }

    val currentBrushColor by rememberUpdatedState(brushColor)
    val currentBrushWidth by rememberUpdatedState(brushWidth)

    Canvas(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        onDrag()
                        currentPathPoints.add(
                            PathPoints(
                                moveToX = change.position.x - dragAmount.x,
                                moveToY = change.position.y - dragAmount.y,
                                lineToX = change.position.x,
                                lineToY = change.position.y
                            )
                        )
                        if (pathList.isNotEmpty()) {
                            pathList.removeLast()
                        }
                        pathList.add(PathData(pathPointsList = currentPathPoints))
                    },
                    onDragStart = { currentPathPoints = mutableListOf() },
                    onDragEnd = {
                        onPathDrawn(
                            Brush(
                                color = currentBrushColor,
                                brushWidth = currentBrushWidth,
                                path = PathData(pathPointsList = currentPathPoints)
                            )
                        )
                        pathList.clear()
                    }
                )
            }
    ) {
        pathList.forEach {
            drawPath(
                color = currentBrushColor,
                path = it.toPath(),
                style = Stroke(currentBrushWidth, cap = StrokeCap.Round)
            )
        }
    }
}

@Composable
private fun ToolPanel(
    buttons: List<EditorButtonSettings>,
    isBrushChosen: Boolean,
    onPrevStateClick: () -> Unit,
    onForwardStateClick: () -> Unit,
    onDeleteAllClick: () -> Unit,
    changeCurrentEditorButton: (EditorButton?) -> Unit,
    changeIsPanelExpanded: (Boolean) -> Unit,
) {
    Column {
        StateFigures(
            onPrevStateClick = onPrevStateClick,
            onForwardStateClick = onForwardStateClick,
            onDeleteAllClick = onDeleteAllClick
        )
        ButtonsPanel(
            buttons = buttons,
            isBrushChosen = isBrushChosen,
            onClick = {
                changeCurrentEditorButton(null)
                it.onClick()
            },
            onLongPress = {
                changeCurrentEditorButton(it.type)
                changeIsPanelExpanded(true)
            }
        )
    }
}

@Composable
private fun StateFigures(
    onPrevStateClick: () -> Unit,
    onForwardStateClick: () -> Unit,
    onDeleteAllClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            IconButton(onClick = onPrevStateClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous state"
                )
            }
            IconButton(onClick = onForwardStateClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Forward state"
                )
            }
        }
        IconButton(onClick = onDeleteAllClick) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.trash),
                contentDescription = "Delete all"
            )
        }
    }
}

@Composable
private fun ButtonsPanel(
    isBrushChosen: Boolean,
    buttons: List<EditorButtonSettings>,
    onClick: (EditorButtonSettings) -> Unit,
    onLongPress: (EditorButtonSettings) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(buttons) { button ->
            val isEnabled = button.type == EditorButton.BRUSH || !isBrushChosen

            Box(
                modifier = Modifier
                    .background(if (isBrushChosen && button.type == EditorButton.BRUSH) Color.LightGray else Color.Unspecified)
                    .pointerInput(isEnabled) {
                        detectTapGestures(
                            onTap = { if (isEnabled) onClick(button) },
                            onLongPress = { if (isEnabled) onLongPress(button) }
                        )
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(button.icon),
                    contentDescription = button.type.toString(),
                    tint = if (isEnabled) Color.Unspecified else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun FigureSettingsPanel(
    pressedCurrentEditorButton: EditorButton?,
    brushColor: Color,
    circleColor: Color,
    squareColor: Color,
    triangleColor: Color,
    polygonColor: Color,
    lineColor: Color,
    backgroundColor: Color,
    brushWidth: Float,
    lineWidth: Float,
    polygonVertices: Int,
    changeCircleColor: (Color) -> Unit,
    changeSquareColor: (Color) -> Unit,
    changeTriangleColor: (Color) -> Unit,
    changePolygonColor: (Color) -> Unit,
    changeLineColor: (Color) -> Unit,
    changePolygonVertices: (Int) -> Unit,
    changeLineWidth: (Float) -> Unit,
    changeBrushColor: (Color) -> Unit,
    changeBrushWidth: (Float) -> Unit,
    changeBackgroundColor: (Color) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .pointerInput(Unit) { } // оставляем пустым, чтобы перехватить жесты, дабы не рисовать сквозь Box
    ) {
        Column {
            when (pressedCurrentEditorButton) {
                EditorButton.BRUSH -> {
                    Column {
                        ColorPicker(
                            currentColor = brushColor,
                            changeColor = changeBrushColor,
                            modifier = Modifier.padding(8.dp)
                        )
                        WidthPicker(
                            currentWidth = brushWidth,
                            changeWidth = changeBrushWidth,
                            modifier = Modifier.padding(bottom = 12.dp, end = 16.dp, start = 16.dp)
                        )
                    }
                }

                EditorButton.CIRCLE -> {
                    Column {
                        ColorPicker(
                            currentColor = circleColor,
                            changeColor = changeCircleColor,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                EditorButton.SQUARE -> {
                    Column {
                        ColorPicker(
                            currentColor = squareColor,
                            changeColor = changeSquareColor,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                EditorButton.TRIANGLE -> {
                    Column {
                        ColorPicker(
                            currentColor = triangleColor,
                            changeColor = changeTriangleColor,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                EditorButton.POLYGON -> {
                    Column {
                        ColorPicker(
                            currentColor = polygonColor,
                            changeColor = changePolygonColor,
                            modifier = Modifier.padding(8.dp)
                        )
                        VerticesPicker(
                            currentVertices = polygonVertices,
                            changeVertices = changePolygonVertices,
                            modifier = Modifier.padding(bottom = 12.dp, end = 16.dp, start = 16.dp)
                        )
                    }
                }

                EditorButton.LINE -> {
                    Column {
                        ColorPicker(
                            currentColor = lineColor,
                            changeColor = changeLineColor,
                            modifier = Modifier.padding(8.dp)
                        )
                        WidthPicker(
                            currentWidth = lineWidth,
                            changeWidth = changeLineWidth,
                            modifier = Modifier.padding(bottom = 12.dp, end = 16.dp, start = 16.dp)
                        )
                    }
                }

                EditorButton.BACKGROUND -> {
                    Column {
                        ColorPicker(
                            currentColor = backgroundColor,
                            changeColor = changeBackgroundColor,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                null -> Unit
            }
        }
    }
}