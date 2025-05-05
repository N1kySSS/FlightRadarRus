package com.ortin.flightradar.presentation.component.bottomsheet

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun WeatherBottomSheet() {
    Text(
        text = "WEATHER",
        style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
    )
}
