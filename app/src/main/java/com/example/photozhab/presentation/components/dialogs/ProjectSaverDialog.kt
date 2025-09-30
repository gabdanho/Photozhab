package com.example.photozhab.presentation.components.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.photozhab.R

/**
 * Диалог для сохранения проекта с вводом имени.
 *
 * @param name Текущее имя проекта.
 * @param onNameChanged Событие при изменении имени.
 * @param onDismiss Действие при закрытии диалога.
 * @param onSave Действие при сохранении.
 * @param modifier Модификатор для внешнего контейнера.
 */
@Composable
fun ProjectSaverDialog(
    name: String,
    onNameChanged: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseDialog(onDismiss, modifier) {
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