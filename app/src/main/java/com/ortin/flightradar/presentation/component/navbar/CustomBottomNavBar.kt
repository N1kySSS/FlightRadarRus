package com.ortin.flightradar.presentation.component.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomBottomNavBar() {
    val interactionSource = remember { MutableInteractionSource() }
    val screens: List<NavigationBarItem> = listOf(
        NavigationBarItem.Settings,
        NavigationBarItem.Weather,
        NavigationBarItem.Filters,
        NavigationBarItem.Notifications,
        NavigationBarItem.Other,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
    ) {

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(74.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                .fillMaxWidth()
                .background(
                    color = Color.Black.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(30.dp)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { item: NavigationBarItem ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                TODO("Add action")
                            },
                        painter = painterResource(item.icon),
                        contentDescription = item.title,
                        tint = Color.White
                    )
                    Text(
                        text = item.title,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W300,
                            fontStyle = FontStyle.Normal,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomBottomNavBarPreview() {
    CustomBottomNavBar()
}
