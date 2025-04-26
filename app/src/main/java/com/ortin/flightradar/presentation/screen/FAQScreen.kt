package com.ortin.flightradar.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ortin.flightradar.presentation.component.header.CustomHeader

@Composable
fun FAQScreen(
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        CustomHeader(
            name = "FAQ",
            onBackPressed = onBackPressed
        )
    }
}
