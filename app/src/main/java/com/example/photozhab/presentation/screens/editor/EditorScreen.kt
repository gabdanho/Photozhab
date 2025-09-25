package com.example.photozhab.presentation.screens.editor

import android.os.Build
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.photozhab.R
import com.example.photozhab.presentation.components.ColorPicker
import com.example.photozhab.presentation.components.VerticesPicker
import com.example.photozhab.presentation.components.WidthPicker
import com.example.photozhab.presentation.model.ButtonPanelSettings
import com.example.photozhab.presentation.model.PathData
import com.example.photozhab.presentation.model.figures.Brush
import com.example.photozhab.presentation.model.figures.Circle
import com.example.photozhab.presentation.model.figures.Figure
import com.example.photozhab.presentation.model.figures.Line
import com.example.photozhab.presentation.model.figures.Polygon
import com.example.photozhab.presentation.model.figures.Square
import com.example.photozhab.presentation.model.figures.Triangle
import com.example.photozhab.presentation.model.figures.TypeFigureButton

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun EditorScreen(viewModel: PhotozhabViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    var isBrushChosen by remember { mutableStateOf(false) }
    var isPanelExpanded by remember { mutableStateOf(false) }
    var typeFigure by remember { mutableStateOf<TypeFigureButton?>(null) }
    val buttons = listOf(
        ButtonPanelSettings(
            icon = R.drawable.brush,
            type = TypeFigureButton.BRUSH
        ) {
            isBrushChosen = !isBrushChosen
        },
        ButtonPanelSettings(
            icon = R.drawable.circle,
            type = TypeFigureButton.CIRCLE
        ) {
            viewModel.addFigure(
                Circle(
                    uiState.circleColor
                )
            )
        },
        ButtonPanelSettings(
            icon = R.drawable.square,
            type = TypeFigureButton.SQUARE
        ) {
            viewModel.addFigure(
                Square(
                    uiState.squareColor
                )
            )
        },
        ButtonPanelSettings(
            icon = R.drawable.triangle,
            type = TypeFigureButton.TRIANGLE
        ) {
            viewModel.addFigure(
                Triangle(uiState.triangleColor)
            )
        },
        ButtonPanelSettings(
            icon = R.drawable.polygon,
            type = TypeFigureButton.POLYGON
        ) {
            viewModel.addFigure(
                Polygon(
                    uiState.polygonColor,
                    uiState.polygonVertices
                )
            )
        },
        ButtonPanelSettings(
            icon = R.drawable.line,
            type = TypeFigureButton.LINE
        ) {
            viewModel.addFigure(
                Line(
                    uiState.lineColor,
                    uiState.lineWidth
                )
            )
        },
        ButtonPanelSettings(
            icon = R.drawable.background,
            type = TypeFigureButton.BACKGROUND
        ) { }
    )

    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(uiState.backgroundColor)
                .weight(1f)
                .clipToBounds()
        ) {
            uiState.figures.forEach { figure ->
                figure.draw()
            }
            if (isBrushChosen) {
                // ОбНУЛЛяем, чтобы скрыть панель настроек фигуры
                DrawingCanvas(
                    addFigure = viewModel::addFigure,
                    brushColor = uiState.brushColor,
                    brushWidth = uiState.brushWidth,
                    resetTypeFigure = { typeFigure = null },
                    changeIsPanelExpanded = { isPanelExpanded = it }
                )
            }

            if (typeFigure != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                    FigureSettingsPanel(
                        typeFigure = typeFigure,
                        changePolygonVertices = viewModel::changePolygonVertices,
                        changeTriangleColor = viewModel::changeTriangleColor,
                        changeCircleColor = viewModel::changeCircleColor,
                        changeSquareColor = viewModel::changeSquareColor,
                        changePolygonColor = viewModel::changePolygonColor,
                        changeLineColor = viewModel::changeLineColor,
                        changeLineWidth = viewModel::changeLineWidth,
                        changeBrushWidth = viewModel::changeBrushWidth,
                        changeBrushColor = viewModel::changeBrushColor,
                        changeBackgroundColor = viewModel::changeBackgroundColor,
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
            isBrushChosen = isBrushChosen,
            isPanelExpanded = isPanelExpanded,
            onPrevStateClick = viewModel::prevState,
            onForwardStateClick = viewModel::forwardState,
            onDeleteAllClick = viewModel::deleteAllFigures,
            changeTypeFigure = { typeFigure = it },
            resetTypeFigure = { typeFigure = null },
            changeIsPanelExpanded = { isPanelExpanded = it }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun DrawingCanvas(
    addFigure: (Figure) -> Unit,
    brushColor: Color,
    brushWidth: Float,
    resetTypeFigure: () -> Unit,
    changeIsPanelExpanded: (Boolean) -> Unit,
) {
    val pathData = remember { mutableStateOf(PathData()) }
    var tempPath by remember { mutableStateOf(Path()) }
    val pathList = remember { mutableStateListOf(PathData()) }

    val currentBrushColor by rememberUpdatedState(brushColor)
    val currentBrushWidth by rememberUpdatedState(brushWidth)
    Canvas(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        // ОбНУЛЛяем, чтобы скрыть панель настроек фигуры
                        resetTypeFigure()
                        changeIsPanelExpanded(false)

                        tempPath.moveTo(
                            change.position.x - dragAmount.x,
                            change.position.y - dragAmount.y
                        )
                        tempPath.lineTo(
                            change.position.x,
                            change.position.y
                        )

                        if (pathList.isNotEmpty()) {
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
                        addFigure(
                            Brush(
                                currentBrushColor,
                                currentBrushWidth,
                                tempPath
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
                path = it.path,
                style = Stroke(currentBrushWidth, cap = StrokeCap.Round)
            )
        }
    }
}

@Composable
fun ToolPanel(
    buttons: List<ButtonPanelSettings>,
    isBrushChosen: Boolean,
    isPanelExpanded: Boolean,
    onPrevStateClick: () -> Unit,
    onForwardStateClick: () -> Unit,
    onDeleteAllClick: () -> Unit,
    changeTypeFigure: (TypeFigureButton?) -> Unit,
    resetTypeFigure: () -> Unit,
    changeIsPanelExpanded: (Boolean) -> Unit,
) {
    Column {
        StateFiguresButtonPanel(
            onPrevStateClick = onPrevStateClick,
            onForwardStateClick = onForwardStateClick,
            onDeleteAllClick = onDeleteAllClick
        )
        FiguresButtonPanel(
            buttons = buttons,
            isBrushChosen = isBrushChosen,
            isPanelExpanded = isPanelExpanded,
            changeTypeFigure = changeTypeFigure,
            resetTypeFigure = resetTypeFigure,
            changeIsPanelExpanded = changeIsPanelExpanded
        )
    }
}

@Composable
fun StateFiguresButtonPanel(
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
        IconButton(onClick = onDeleteAllClick) { // TODO("Добавить диалог, чтобы пользователь дал подтверждение")
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.trash),
                contentDescription = "Delete all"
            )
        }
    }
}

@Composable
fun FiguresButtonPanel(
    buttons: List<ButtonPanelSettings>,
    isBrushChosen: Boolean,
    isPanelExpanded: Boolean,
    changeTypeFigure: (TypeFigureButton) -> Unit,
    resetTypeFigure: () -> Unit,
    changeIsPanelExpanded: (Boolean) -> Unit,
) {
    val updatedIsPanelExpanded by rememberUpdatedState(isPanelExpanded)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(buttons) { button ->
            val isEnabled = if (button.type != TypeFigureButton.BRUSH) !isBrushChosen else true

            Box(
                modifier = Modifier
                    .background(if (isBrushChosen && button.type == TypeFigureButton.BRUSH) Color.LightGray else Color.Unspecified)
                    .then(
                        if (isEnabled) Modifier.pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    resetTypeFigure()
                                    changeIsPanelExpanded(false)
                                    button.onClick()
                                },
                                onLongPress = {
                                    if (updatedIsPanelExpanded) {
                                        changeIsPanelExpanded(false)
                                        resetTypeFigure()
                                    } else {
                                        changeIsPanelExpanded(true)
                                        changeTypeFigure(button.type)
                                    }
                                }
                            )
                        } else Modifier
                    )
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
fun FigureSettingsPanel(
    typeFigure: TypeFigureButton?,
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
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.LightGray,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .pointerInput(Unit) { } // оставляем пустым, чтобы перехватить жесты, дабы не рисовать сквозь Box
    ) {
        when (typeFigure) {
            TypeFigureButton.BRUSH -> {
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

            TypeFigureButton.CIRCLE -> {
                Column {
                    ColorPicker(
                        currentColor = circleColor,
                        changeColor = changeCircleColor,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            TypeFigureButton.SQUARE -> {
                Column {
                    ColorPicker(
                        currentColor = squareColor,
                        changeColor = changeSquareColor,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            TypeFigureButton.TRIANGLE -> {
                Column {
                    ColorPicker(
                        currentColor = triangleColor,
                        changeColor = changeTriangleColor,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            TypeFigureButton.POLYGON -> {
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

            TypeFigureButton.LINE -> {
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

            TypeFigureButton.BACKGROUND -> {
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