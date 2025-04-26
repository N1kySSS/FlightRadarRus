package com.ortin.flightradar.presentation.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun CustomHeader(
    name: String,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth()
            .background(color = Background.copy(alpha = 1f))
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter),
            text = name,
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
                .padding(bottom = 24.dp)
                .align(Alignment.BottomEnd)
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
