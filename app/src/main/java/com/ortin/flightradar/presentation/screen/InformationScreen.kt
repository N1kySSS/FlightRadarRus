package com.ortin.flightradar.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.flightradar.R
import com.ortin.flightradar.presentation.component.developercard.DeveloperCard
import com.ortin.flightradar.presentation.component.header.CustomHeader

@Composable
fun InformationScreen(
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        CustomHeader(
            name = "О нас",
            onBackPressed = onBackPressed
        )
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            text = "Разработчики",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W300,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
            ),
        )
        DeveloperCard(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 16.dp
            ),
            imageId = R.drawable.icon_n,
            text = "Mobile developer: https://github.com/N1kySSS"
        )
        DeveloperCard(
            modifier = Modifier.padding(horizontal = 10.dp),
            imageId = R.drawable.icon_d,
            text = "Backend developer: https://github.com/dmitrygortex"
        )
        DeveloperCard(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 16.dp
            ),
            imageId = R.drawable.icon_m,
            text = "Frontend developer: https://github.com/MatveyJI"
        )
    }
}
