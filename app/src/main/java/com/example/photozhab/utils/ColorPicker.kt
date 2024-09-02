package com.example.photozhab.utils

import androidx.compose.foundation.background
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

fun Float.toColorInt(): Int = (this * 255 + 0.5f).toInt()

@Composable
fun ColorSlider(
    label: String,
    valueState: MutableState<Float>,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(text = label)
        Slider(
            value = valueState.value,
            onValueChange = valueState.component2(),
            colors = SliderDefaults.colors(
                activeTrackColor = color
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = valueState.value.toColorInt().toString(),
            modifier = Modifier.width(25.dp),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ColorPicker(currentColor: Color, changeColor: (Color) -> Unit) {
    val red = rememberSaveable { mutableFloatStateOf(currentColor.red) }
    val green = rememberSaveable { mutableFloatStateOf(currentColor.green) }
    val blue = rememberSaveable { mutableFloatStateOf(currentColor.blue) }

    val color by remember {
        derivedStateOf {
            Color(red.value, green.value, blue.value)
        }
    }

    LaunchedEffect(color) {
        changeColor(color)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Цвет",
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(40.dp)
                .background(color, shape = MaterialTheme.shapes.large)
        )

        // Sliders for adjusting RGBA values
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            ColorSlider("R", red, Color.Red)
            ColorSlider("G", green, Color.Green)
            ColorSlider("B", blue, Color.Blue)
        }
    }
}