package com.ortin.flightradar.presentation.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.flightradar.R

@Composable
fun WeatherBottomSheet() {
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = "АКТАУЛЬНАЯ ПОГОДА",
        style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
    )
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .wrapContentSize()
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.photo_2),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Image weather"
            )
            Text(
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.Gray.copy(alpha = 0.65f),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp
                        )
                    ),
                text = "Выкл.", // TODO - change later based on state
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                ),
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Top)
                .padding(horizontal = 10.dp),
            text = "Актуальная погода для аэропортов по всему миру.",
            textAlign = TextAlign.Start,
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
            ),
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 10.dp),
        thickness = 2.dp
    )
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = "ОБЛАЧНОСТЬ",
        style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
    )
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .wrapContentSize()
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.photo_1),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Cloud cover"
            )
            Text(
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.Gray.copy(alpha = 0.65f),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp
                        )
                    ),
                text = "Выкл.", // TODO - change later based on state
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                ),
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Top)
                .padding(horizontal = 10.dp),
            text = "Отображение на карте облачного покрова по всему миру.",
            textAlign = TextAlign.Start,
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
            ),
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 10.dp),
        thickness = 2.dp
    )
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = "ОБЩЕЕ КОЛИЧЕСТВО ОСАДКОВ",
        style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
    )
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .wrapContentSize()
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.photo_3),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Rain"
            )
            Text(
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.Gray.copy(alpha = 0.65f),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp
                        )
                    ),
                text = "Выкл.", // TODO - change later based on state
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                ),
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Top)
                .padding(horizontal = 10.dp),
            text = "Актуальное отображение осадков по всему миру на карте в режиме реального времени.",
            textAlign = TextAlign.Start,
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
            ),
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 10.dp),
        thickness = 2.dp
    )
    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = "ИНТЕНСИВНЫЕ ОСАДКИ",
        style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
    )
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .wrapContentSize()
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.photo_4),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Rain"
            )
            Text(
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.Gray.copy(alpha = 0.65f),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp
                        )
                    ),
                text = "Выкл.", // TODO - change later based on state
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                ),
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.Top)
                .padding(horizontal = 10.dp),
            text = "Отображение интенсивности осадков на карте по всему миру.",
            textAlign = TextAlign.Start,
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
            ),
        )
    }
}
