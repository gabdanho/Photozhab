package com.example.photozhab.presentation.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.example.photozhab.presentation.ui.theme.defaultDimensions

@Composable
fun BaseDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = modifier) {
            Column(
                verticalArrangement = Arrangement.spacedBy(defaultDimensions.small),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(defaultDimensions.medium)
                    .verticalScroll(scrollState),
                content = content
            )
        }
    }
}