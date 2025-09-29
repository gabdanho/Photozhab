package com.example.photozhab.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.photozhab.R

@Composable
fun WidthPicker(
    currentWidth: Float,
    changeWidth: (Float) -> Unit,
    modifier: Modifier = Modifier,
    steps: Int = 100,
) {
    var width by remember { mutableFloatStateOf(currentWidth / 100) }
    var resultWidth by remember { mutableFloatStateOf(currentWidth) }

    LaunchedEffect(resultWidth) {
        changeWidth(resultWidth)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(stringResource(R.string.text_line_width))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Slider(
                value = width,
                onValueChange = {
                    width = it
                    resultWidth = (width * 100)
                },
                modifier = Modifier.weight(1f),
                steps = steps
            )
            Text(
                text = resultWidth.toInt().toString(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}