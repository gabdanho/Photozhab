package com.example.photozhab.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toColorLong
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    currentColor: Color,
    changeColor: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val red = rememberSaveable { mutableFloatStateOf(currentColor.red) }
    val green = rememberSaveable { mutableFloatStateOf(currentColor.green) }
    val blue = rememberSaveable { mutableFloatStateOf(currentColor.blue) }

    val color by remember {
        derivedStateOf {
            Color(red.value, green.value, blue.value)
        }
    }

    LaunchedEffect(color) {
        changeColor(color.toColorLong())
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "Цвет",
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
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