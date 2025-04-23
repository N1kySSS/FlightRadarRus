package com.ortin.flightradar.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.flightradar.R
import com.ortin.flightradar.presentation.util.clickableWithoutIndication
import com.ortin.flightradar.ui.theme.Background
import com.ortin.flightradar.ui.theme.Primary

@Composable
fun InformationScreen(
    onBackPressed: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Box(
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth()
                .background(color = Background)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "О нас",
                style = TextStyle(
                    color = Primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W300,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                ),
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickableWithoutIndication(
                        onClick = {
                            onBackPressed()
                        }
                    ),
                painter = painterResource(R.drawable.delete),
                contentDescription = "Go back"
            )
        }
    }
}
