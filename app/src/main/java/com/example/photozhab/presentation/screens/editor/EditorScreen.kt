package com.example.photozhab.presentation.screens.editor

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.fromColorLong
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.photozhab.R
import com.example.photozhab.presentation.components.dialogs.SavedProjectDialog
import com.example.photozhab.presentation.components.ColorPicker
import com.example.photozhab.presentation.components.dialogs.DeleteDataDialog
import com.example.photozhab.presentation.components.VerticesPicker
import com.example.photozhab.presentation.components.WidthPicker
import com.example.photozhab.presentation.components.dialogs.ProjectSaverDialog
import com.example.photozhab.presentation.model.EditorButtonSettings
import com.example.photozhab.presentation.model.Figure
import com.example.photozhab.presentation.model.EditorButton
import com.example.photozhab.presentation.model.PathData
import com.example.photozhab.presentation.model.PathPoints
import com.example.photozhab.presentation.model.EditorButtonsProperties
import com.example.photozhab.presentation.model.LoadingState
import com.example.photozhab.presentation.model.ToolsStateFunctions
import com.example.photozhab.presentation.screens.editor.locals.LocalEditorButtonsProperties
import com.example.photozhab.presentation.screens.editor.locals.LocalToolsStateFunctions
import com.example.photozhab.presentation.ui.theme.defaultDimensions
import com.example.photozhab.presentation.utils.saveToBitmap
import com.example.photozhab.presentation.utils.showUiMessage
import kotlinx.coroutines.launch

@Composable
fun EditorScreen(viewModel: EditorScreenViewModel = hiltViewModel<EditorScreenViewModel>()) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val graphicsLayer = rememberGraphicsLayer()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

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
            viewModel.addFigure(figure = Figure.Circle(color = Color.fromColorLong(uiState.circleColor)))
        },
        EditorButtonSettings(
            icon = R.drawable.square,
            type = EditorButton.SQUARE
        ) {
            viewModel.addFigure(figure = Figure.Square(color = Color.fromColorLong(uiState.squareColor)))
        },
        EditorButtonSettings(
            icon = R.drawable.triangle,
            type = EditorButton.TRIANGLE
        ) {
            viewModel.addFigure(figure = Figure.Triangle(color = Color.fromColorLong(uiState.triangleColor)))
        },
        EditorButtonSettings(
            icon = R.drawable.polygon,
            type = EditorButton.POLYGON
        ) {
            viewModel.addFigure(
                figure = Figure.Polygon(
                    color = Color.fromColorLong(uiState.polygonColor),
                    vertices = uiState.polygonVertices
                )
            )
        },
        EditorButtonSettings(
            icon = R.drawable.line,
            type = EditorButton.LINE
        ) {
            viewModel.addFigure(
                figure = Figure.Line(
                    color = Color.fromColorLong(uiState.lineColor),
                    lineWidth = uiState.lineWidth
                )
            )
        },
        EditorButtonSettings(
            icon = R.drawable.background,
            type = EditorButton.BACKGROUND
        ) { }
    )

    DisposableEffect(lifecycleOwner) {
        val observer = EditorScreenLifecycleObserver { viewModel.saveTempCanvas() }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(uiState.uiMessage) {
        uiState.uiMessage?.let {
            context.showUiMessage(
                uiMessage = it,
                clearMessage = { viewModel.clearMessage() }
            )
        }
    }

    BackHandler(enabled = uiState.isPanelExpanded) {
        viewModel.changeIsPanelExpanded(value = false)
    }

    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .drawWithContent {
                    graphicsLayer.record {
                        this@drawWithContent.drawContent()
                    }
                    drawLayer(graphicsLayer)
                }
                .fillMaxSize()
                .weight(1f)
                .clipToBounds()
                .drawBehind {
                    drawRect(color = Color.fromColorLong(uiState.backgroundColor))
                }
        ) {
            uiState.figures.forEach { figure ->
                figure.DrawFigure()
            }
            if (uiState.isBrushChosen) {
                DrawingCanvas(
                    brushColor = Color.fromColorLong(uiState.brushColor),
                    brushWidth = uiState.brushWidth,
                    onPathDrawn = { viewModel.addFigure(figure = it) },
                    onDrag = {
                        viewModel.changeIsPanelExpanded(value = false)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            if (uiState.isPanelExpanded) {
                CompositionLocalProvider(
                    LocalEditorButtonsProperties provides EditorButtonsProperties(
                        pressedCurrentEditorButton = uiState.currentEditorButton,
                        changePolygonVertices = { viewModel.changePolygonVertices(vertices = it) },
                        changeTriangleColor = { viewModel.changeTriangleColor(colorLong = it) },
                        changeCircleColor = { viewModel.changeCircleColor(colorLong = it) },
                        changeSquareColor = { viewModel.changeSquareColor(colorLong = it) },
                        changePolygonColor = { viewModel.changePolygonColor(colorLong = it) },
                        changeLineColor = { viewModel.changeLineColor(colorLong = it) },
                        changeLineWidth = { viewModel.changeLineWidth(width = it) },
                        changeBrushWidth = { viewModel.changeBrushWidth(width = it) },
                        changeBrushColor = { viewModel.changeBrushColor(colorLong = it) },
                        changeBackgroundColor = { viewModel.changeBackgroundColor(colorLong = it) },
                        brushColor = Color.fromColorLong(uiState.brushColor),
                        circleColor = Color.fromColorLong(uiState.circleColor),
                        squareColor = Color.fromColorLong(uiState.squareColor),
                        triangleColor = Color.fromColorLong(uiState.triangleColor),
                        polygonColor = Color.fromColorLong(uiState.polygonColor),
                        backgroundColor = Color.fromColorLong(uiState.backgroundColor),
                        lineColor = Color.fromColorLong(uiState.lineColor),
                        lineWidth = uiState.lineWidth,
                        brushWidth = uiState.brushWidth,
                        polygonVertices = uiState.polygonVertices
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        FigureSettingsPanel(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.LightGray)
                        )
                    }
                }
            }
        }
        CompositionLocalProvider(
            LocalToolsStateFunctions provides ToolsStateFunctions(
                onSaveToGalleryClick = {
                    coroutineScope.launch {
                        viewModel.saveToGallery(bitmap = graphicsLayer.saveToBitmap())
                    }
                },
                onSavesClick = { viewModel.changeDialog(dialog = EditorScreensDialog.SavedProjects) },
                onPrevStateClick = { viewModel.prevState() },
                onForwardStateClick = { viewModel.forwardState() },
                onDeleteAllClick = { viewModel.changeDialog(dialog = EditorScreensDialog.Warning) },
                changeCurrentEditorButton = { viewModel.changeCurrentEditorButton(editorButton = it) },
                changeIsPanelExpanded = { viewModel.changeIsPanelExpanded(value = it) },
                onPanelClick = { viewModel.changeCurrentEditorButton(null) },
                onButtonClick = {
                    viewModel.changeCurrentEditorButton(null)
                    it.onClick()
                },
                onLongPressButton = {
                    viewModel.changeCurrentEditorButton(it.type)
                    viewModel.changeIsPanelExpanded(true)
                },
            )
        ) {
            ToolPanel(
                buttons = buttons,
                isBrushChosen = uiState.isBrushChosen,
            )
        }
    }

    when (uiState.dialog) {
        EditorScreensDialog.DeleteSavedProject -> {
            DeleteDataDialog(
                onDismiss = {
                    viewModel.changeDialog(dialog = EditorScreensDialog.SavedProjects)
                },
                onAccess = {
                    viewModel.deleteProject()
                    viewModel.changeDialog(dialog = EditorScreensDialog.SavedProjects)
                }
            )
        }

        EditorScreensDialog.ProjectSaver -> {
            ProjectSaverDialog(
                name = uiState.projectNameValue,
                onNameChanged = { viewModel.changeProjectNameValue(value = it) },
                onDismiss = {
                    viewModel.changeDialog(dialog = EditorScreensDialog.SavedProjects)
                },
                onSave = {
                    viewModel.saveProject()
                    viewModel.changeDialog(dialog = EditorScreensDialog.None)
                }
            )
        }

        EditorScreensDialog.SavedProjects -> {
            SavedProjectDialog(
                canvases = uiState.savedProjects,
                onDismiss = { viewModel.changeDialog(dialog = EditorScreensDialog.None) },
                onSave = {
                    viewModel.changeDialog(dialog = EditorScreensDialog.ProjectSaver)
                },
                onDelete = { id ->
                    viewModel.changeSavedProjectIdToDelete(id)
                    viewModel.changeDialog(dialog = EditorScreensDialog.DeleteSavedProject)
                },
                onOpen = { id ->
                    viewModel.getProjectById(id)
                    viewModel.changeDialog(dialog = EditorScreensDialog.None)
                },
                modifier = Modifier.fillMaxSize(0.9f)
            )
        }

        EditorScreensDialog.Warning -> {
            DeleteDataDialog(
                onDismiss = { viewModel.changeDialog(dialog = EditorScreensDialog.None) },
                onAccess = {
                    viewModel.deleteAllFiguresAndClearBackground()
                    viewModel.changeDialog(dialog = EditorScreensDialog.None)
                }
            )
        }

        EditorScreensDialog.None -> {}
    }

    if (uiState.loadingState is LoadingState.Loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = defaultDimensions.medium)
            )
        }
    }
}

@Composable
private fun DrawingCanvas(
    brushColor: Color,
    brushWidth: Float,
    onPathDrawn: (Figure) -> Unit,
    modifier: Modifier = Modifier,
    onDrag: () -> Unit = { },
) {
    var currentPathPoints = remember { mutableListOf<PathPoints>() }
    val pathList = remember { mutableStateListOf(PathData()) }

    val currentBrushColor by rememberUpdatedState(brushColor)
    val currentBrushWidth by rememberUpdatedState(brushWidth)

    Canvas(
        modifier
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
                            pathList.removeAt(pathList.lastIndex)
                        }
                        pathList.add(PathData(pathPointsList = currentPathPoints))
                    },
                    onDragStart = { currentPathPoints = mutableListOf() },
                    onDragEnd = {
                        onPathDrawn(
                            Figure.Brush(
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
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        StateFigures(modifier = Modifier.fillMaxWidth())
        ButtonsPanel(
            buttons = buttons,
            isBrushChosen = isBrushChosen,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun StateFigures(modifier: Modifier = Modifier) {
    val toolsStateFunctions = LocalToolsStateFunctions.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .clickable { toolsStateFunctions.onPanelClick() }
    ) {
        Row {
            IconButton(onClick = toolsStateFunctions.onPrevStateClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_previous_state)
                )
            }
            IconButton(onClick = toolsStateFunctions.onForwardStateClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = stringResource(R.string.content_forward_state)
                )
            }
        }
        Row {
            IconButton(onClick = toolsStateFunctions.onSaveToGalleryClick) {
                Icon(
                    modifier = Modifier.size(defaultDimensions.iconButtonSize),
                    painter = painterResource(R.drawable.gallery_save),
                    contentDescription = stringResource(R.string.content_save_to_gallery)
                )
            }
            IconButton(onClick = toolsStateFunctions.onSavesClick) {
                Icon(
                    modifier = Modifier.size(defaultDimensions.iconButtonSize),
                    painter = painterResource(R.drawable.diskette),
                    contentDescription = stringResource(R.string.content_saves)
                )
            }
            IconButton(onClick = toolsStateFunctions.onDeleteAllClick) {
                Icon(
                    modifier = Modifier.size(defaultDimensions.iconButtonSize),
                    painter = painterResource(R.drawable.trash),
                    contentDescription = stringResource(R.string.content_delete_all)
                )
            }
        }
    }
}

@Composable
private fun ButtonsPanel(
    isBrushChosen: Boolean,
    buttons: List<EditorButtonSettings>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(buttons) { button ->
            val isEnabled = button.type == EditorButton.BRUSH || !isBrushChosen

            EditorButtonItem(
                button = button,
                isBrushChosen = isBrushChosen,
                isEnabled = isEnabled
            )
        }
    }
}

@Composable
private fun FigureSettingsPanel(modifier: Modifier = Modifier) {
    val buttonsProperties = LocalEditorButtonsProperties.current

    Box(
        modifier = modifier
            .pointerInput(Unit) { } // оставляем пустым, чтобы перехватить жесты, дабы не рисовать сквозь Box
    ) {
        Column {
            when (buttonsProperties.pressedCurrentEditorButton) {
                EditorButton.BRUSH -> BrushEditor()

                EditorButton.CIRCLE -> CircleEditor()

                EditorButton.SQUARE -> SquareEditor()

                EditorButton.TRIANGLE -> TriangleEditor()

                EditorButton.POLYGON -> PolygonEditor()

                EditorButton.LINE -> LineEditor()

                EditorButton.BACKGROUND -> BackgroundEditor()

                null -> Unit
            }
        }
    }
}

@Composable
private fun BrushEditor(modifier: Modifier = Modifier) {
    val buttonsProperties = LocalEditorButtonsProperties.current

    Column(modifier = modifier) {
        ColorPicker(
            currentColor = buttonsProperties.brushColor,
            changeColor = buttonsProperties.changeBrushColor,
            modifier = Modifier.padding(defaultDimensions.verySmall)
        )
        WidthPicker(
            currentWidth = buttonsProperties.brushWidth,
            changeWidth = buttonsProperties.changeBrushWidth,
            modifier = Modifier.padding(
                bottom = defaultDimensions.verySmall,
                end = defaultDimensions.medium,
                start = defaultDimensions.medium
            )
        )
    }
}

@Composable
private fun CircleEditor(modifier: Modifier = Modifier) {
    val buttonsProperties = LocalEditorButtonsProperties.current

    Column(modifier = modifier) {
        ColorPicker(
            currentColor = buttonsProperties.circleColor,
            changeColor = buttonsProperties.changeCircleColor,
            modifier = Modifier.padding(defaultDimensions.verySmall)
        )
    }
}

@Composable
private fun SquareEditor(modifier: Modifier = Modifier) {
    val buttonsProperties = LocalEditorButtonsProperties.current

    Column(modifier = modifier) {
        ColorPicker(
            currentColor = buttonsProperties.squareColor,
            changeColor = buttonsProperties.changeSquareColor,
            modifier = Modifier.padding(defaultDimensions.verySmall)
        )
    }
}

@Composable
private fun TriangleEditor(modifier: Modifier = Modifier) {
    val buttonsProperties = LocalEditorButtonsProperties.current

    Column(modifier = modifier) {
        ColorPicker(
            currentColor = buttonsProperties.triangleColor,
            changeColor = buttonsProperties.changeTriangleColor,
            modifier = Modifier.padding(defaultDimensions.verySmall)
        )
    }
}

@Composable
private fun PolygonEditor(modifier: Modifier = Modifier) {
    val buttonsProperties = LocalEditorButtonsProperties.current

    Column(modifier = modifier) {
        ColorPicker(
            currentColor = buttonsProperties.polygonColor,
            changeColor = buttonsProperties.changePolygonColor,
            modifier = Modifier.padding(defaultDimensions.verySmall)
        )
        VerticesPicker(
            currentVertices = buttonsProperties.polygonVertices,
            changeVertices = buttonsProperties.changePolygonVertices,
            modifier = Modifier.padding(
                bottom = defaultDimensions.small,
                end = defaultDimensions.medium,
                start = defaultDimensions.medium
            )
        )
    }
}

@Composable
private fun BackgroundEditor(modifier: Modifier = Modifier) {
    val buttonsProperties = LocalEditorButtonsProperties.current

    Column(modifier = modifier) {
        ColorPicker(
            currentColor = buttonsProperties.backgroundColor,
            changeColor = buttonsProperties.changeBackgroundColor,
            modifier = Modifier.padding(defaultDimensions.verySmall)
        )
    }
}

@Composable
private fun LineEditor(modifier: Modifier = Modifier) {
    val buttonsProperties = LocalEditorButtonsProperties.current

    Column(modifier = modifier) {
        ColorPicker(
            currentColor = buttonsProperties.lineColor,
            changeColor = buttonsProperties.changeLineColor,
            modifier = Modifier.padding(defaultDimensions.verySmall)
        )
        WidthPicker(
            currentWidth = buttonsProperties.lineWidth,
            changeWidth = buttonsProperties.changeLineWidth,
            modifier = Modifier.padding(
                bottom = defaultDimensions.small,
                end = defaultDimensions.medium,
                start = defaultDimensions.medium
            )
        )
    }
}

@Composable
private fun EditorButtonItem(
    button: EditorButtonSettings,
    isBrushChosen: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    val toolsStateFunctions = LocalToolsStateFunctions.current

    Box(
        modifier = modifier
            .background(if (isBrushChosen && button.type == EditorButton.BRUSH) Color.LightGray else Color.Unspecified)
            .pointerInput(isEnabled) {
                detectTapGestures(
                    onTap = { if (isEnabled) toolsStateFunctions.onButtonClick(button) },
                    onLongPress = {
                        if (isEnabled) toolsStateFunctions.onLongPressButton(
                            button
                        )
                    }
                )
            }
            .padding(defaultDimensions.medium)
    ) {
        Icon(
            painter = painterResource(button.icon),
            contentDescription = button.type.toString(),
            tint = if (isEnabled) Color.Unspecified else Color.Gray,
            modifier = Modifier.size(defaultDimensions.iconButtonSize)
        )
    }
}