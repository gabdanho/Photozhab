package com.example.photozhab.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
fun WidthPicker(currentWidth: Float, changeWidth: (Float) -> Unit) {
    var width by remember { mutableStateOf(currentWidth / 100) }
    var resultWidth by remember { mutableStateOf(currentWidth) }

    LaunchedEffect(resultWidth) {
        changeWidth(resultWidth)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 12.dp, end = 16.dp, start = 16.dp)
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