package com.ortin.flightradar.presentation.component.developercard

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.flightradar.presentation.util.clickableWithoutIndication
import com.ortin.flightradar.ui.theme.Background
import com.ortin.flightradar.ui.theme.ButtonColor

@Composable
fun DeveloperCard(
    modifier: Modifier,
    imageId: Int,
    text: String
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .clickableWithoutIndication(
                enabled = true,
                onClick = {
                    val link = text.split(" ")[2]

                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(link)
                    }

                    context.startActivity(intent)
                }
            )
            .height(150.dp)
            .fillMaxWidth()
            .background(
                color = Background.copy(alpha = 1f),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(12.dp),
                color = ButtonColor
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(100.dp)
                .clip(CircleShape),
            painter = painterResource(imageId),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            contentDescription = "Icon"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.W300,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Start,
            ),
        )
    }
}
