package com.example.photozhab.presentation.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.photozhab.R
import com.example.photozhab.presentation.ui.theme.defaultDimensions

/**
 * Диалог подтверждения удаления данных.
 *
 * @param onDismiss Действие при отмене.
 * @param onAccess Действие при подтверждении удаления.
 * @param modifier Модификатор для внешнего контейнера.
 */
@Composable
fun DeleteDataDialog(
    onDismiss: () -> Unit,
    onAccess: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseDialog(onDismiss, modifier) {
        Text(
            text = stringResource(R.string.text_want_to_delete_the_data)
        )
        Spacer(modifier = Modifier.height(defaultDimensions.medium))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onAccess) {
                Text(text = stringResource(R.string.button_yes))
            }
            Spacer(modifier = Modifier.width(defaultDimensions.medium))
            Button(onClick = onDismiss) {
                Text(text = stringResource(R.string.button_no))
            }
        }
    }
}