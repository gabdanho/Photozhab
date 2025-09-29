package com.example.photozhab.presentation.components.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.example.photozhab.R
import com.example.photozhab.presentation.model.CanvasInfo
import com.example.photozhab.presentation.ui.theme.defaultDimensions

@Composable
fun SavedProjectDialog(
    canvases: List<CanvasInfo>,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onDelete: (Int) -> Unit,
    onOpen: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = modifier) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(defaultDimensions.verySmall),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(defaultDimensions.medium)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.text_saved_projects),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                if (canvases.isNotEmpty()) {
                    items(canvases) { canvasInfo ->
                        SaveInfo(
                            canvasInfo = canvasInfo,
                            onOpen = onOpen,
                            onDelete = onDelete,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else {
                    item {
                        Text(
                            text = stringResource(R.string.text_there_are_no_saved_projects),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item {
                    Button(
                        onClick = { onSave() }
                    ) {
                        Text(text = stringResource(R.string.text_save_this_project))
                    }
                }
            }
        }
    }
}

@Composable
private fun SaveInfo(
    canvasInfo: CanvasInfo,
    onOpen: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOpen(canvasInfo.id) }
        ) {
            Text(
                text = canvasInfo.name,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(defaultDimensions.widthDialogWeight)
            )
            IconButton(
                onClick = { onDelete(canvasInfo.id) }
            ) {
                Icon(
                    imageVector = Icons.Default.RestoreFromTrash,
                    contentDescription = stringResource(
                        R.string.text_delete_canvas,
                        canvasInfo.name
                    )
                )
            }
        }
    }
}