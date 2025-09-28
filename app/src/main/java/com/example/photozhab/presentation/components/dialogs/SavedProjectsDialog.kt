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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.photozhab.presentation.model.CanvasInfo

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
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                item {
                    Text(
                        text = "Saved projects",
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
                            text = "There are no saved projects",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item {
                    Button(
                        onClick = { onSave() }
                    ) {
                        Text(text = "Save this project")
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
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            IconButton(
                onClick = { onDelete(canvasInfo.id) }
            ) {
                Icon(
                    imageVector = Icons.Default.RestoreFromTrash,
                    contentDescription = "Delete canvas: ${canvasInfo.name}"
                )
            }
        }
    }
}