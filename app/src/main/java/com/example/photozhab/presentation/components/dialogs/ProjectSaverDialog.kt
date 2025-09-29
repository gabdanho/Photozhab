package com.example.photozhab.presentation.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.photozhab.R
import com.example.photozhab.presentation.ui.theme.defaultDimensions

@Composable
fun ProjectSaverDialog(
    name: String,
    onNameChanged: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = modifier) {
            Column(
                verticalArrangement = Arrangement.spacedBy(defaultDimensions.small),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(defaultDimensions.medium)
            ) {
                Text(
                    text = stringResource(R.string.text_enter_the_name)
                )
                TextField(
                    value = name,
                    onValueChange = { onNameChanged(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = { onSave() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.button_save))
                }
            }
        }
    }
}