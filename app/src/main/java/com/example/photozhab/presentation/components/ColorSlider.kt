package com.example.photozhab.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.photozhab.presentation.ui.theme.defaultDimensions
import com.example.photozhab.presentation.utils.toColorInt

/**
 * Слайдер для изменения одного из компонентов цвета (R, G, B).
 *
 * @param label Метка компонента цвета.
 * @param valueState Состояние компонента цвета.
 * @param color Цвет слайдера.
 * @param modifier Модификатор для внешнего контейнера.
 */
@Composable
fun ColorSlider(
    label: String,
    valueState: MutableState<Float>,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(text = label)
        Slider(
            value = valueState.value,
            onValueChange = valueState.component2(),
            colors = SliderDefaults.colors(
                activeTrackColor = color
            ),
            modifier = Modifier.weight(defaultDimensions.fullWeight)
        )
        Text(
            text = valueState.value.toColorInt().toString(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall
        )
    }
}