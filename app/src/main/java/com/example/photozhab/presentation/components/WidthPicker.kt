package com.example.photozhab.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WidthPicker(
    currentWidth: Float,
    changeWidth: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    var width by remember { mutableStateOf(currentWidth / 100) }
    var resultWidth by remember { mutableStateOf(currentWidth) }

    LaunchedEffect(resultWidth) {
        changeWidth(resultWidth)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text("Ширина линии")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Slider(
                value = width,
                onValueChange = {
                    width = it
                    resultWidth = (width * 100)
                },
                modifier = Modifier.weight(1f),
                steps = 100
            )
            Text(
                text = resultWidth.toInt().toString(),
                modifier = Modifier.width(25.dp),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}