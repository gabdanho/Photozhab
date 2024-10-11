package com.example.photozhab.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
fun VerticesPicker(currentVertices: Int, changeVertices: (Int) -> Unit) {
    var vertices by remember { mutableStateOf(currentVertices.toFloat() / 100) }
    var verticesResult by remember { mutableStateOf(currentVertices) }

    LaunchedEffect(verticesResult) {
        changeVertices(verticesResult)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 12.dp, end = 16.dp, start = 16.dp)
    ) {
        Text("Углы")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Slider(
                value = vertices,
                onValueChange = {
                    vertices = it
                    verticesResult = (vertices * 100).toInt()
                },
                modifier = Modifier.weight(1f),
                steps = 47,
                valueRange = 0.03f..0.5f
            )
            Text(
                text = verticesResult.toString(),
                modifier = Modifier.width(25.dp),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}