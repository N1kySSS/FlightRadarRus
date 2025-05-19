package com.ortin.flightradar.presentation.component.topsheet

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.flightradar.R
import com.ortin.flightradar.presentation.util.clickableWithoutIndication
import com.ortin.flightradar.ui.theme.Additional
import com.ortin.flightradar.ui.theme.Background
import com.ortin.flightradar.ui.theme.Primary

@Composable
fun CustomTopSheet(isVisible: Boolean) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight * 3 / 4

    val animatedHeight by animateDpAsState(
        targetValue = if (isVisible) sheetHeight else 0.dp,
        animationSpec = tween(durationMillis = 800),
        label = "SheetHeight"
    )

    Box(
        modifier = Modifier
            .padding(top = 110.dp)
            .height(animatedHeight)
            .fillMaxWidth()
            .background(
                Background,
                RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )
            )
    ) {
        /**
         * Это замоканные данные,в дальнейшем, они будут получены с бэкенда
         **/
        if (animatedHeight > 0.dp) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = "История поиска",
                        style = TextStyle(
                            color = Primary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W300,
                            fontStyle = FontStyle.Normal,
                            textAlign = TextAlign.Center,
                        ),
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .wrapContentSize()
                            .clickableWithoutIndication {
                                // TODO (Add action)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Очистить историю",
                            style = TextStyle(
                                color = Additional,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W300,
                                fontStyle = FontStyle.Normal,
                                textAlign = TextAlign.Center,
                            ),
                        )
                        Icon(
                            painter = painterResource(R.drawable.delete),
                            contentDescription = "Стереть текст",
                            tint = Additional
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Ваша история поиска чиста",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                    ),
                )
                HorizontalDivider(thickness = 2.dp)
                Text(
                    modifier = Modifier.padding(vertical = 20.dp),
                    text = "Статистика",
                    style = TextStyle(
                        color = Primary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W300,
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                    ),
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Самолетов",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                fontStyle = FontStyle.Normal,
                                textAlign = TextAlign.Center,
                            ),
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Аэропортов",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                fontStyle = FontStyle.Normal,
                                textAlign = TextAlign.Center,
                            ),
                        )
                    }
                    VerticalDivider(
                        modifier = Modifier.height(60.dp),
                        thickness = 2.dp
                    )
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Eror 404",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                fontStyle = FontStyle.Normal,
                                textAlign = TextAlign.Center,
                            ),
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "5",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                fontStyle = FontStyle.Normal,
                                textAlign = TextAlign.Center,
                            ),
                        )
                    }
                }
                HorizontalDivider(thickness = 2.dp)
                Text(
                    modifier = Modifier.padding(vertical = 20.dp),
                    text = "Избранное",
                    style = TextStyle(
                        color = Primary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W300,
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                    ),
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1338f / 964f)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(R.drawable.preview_image),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image add favourite preview"
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Здесь будут показаны ваши избранные самолеты, аэропорты, рейсы. " +
                            "Чтобы добавить в избранное, нажмите на значок звездочки.",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W300,
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomTopSheetPreview() {
    var isSheetVisible by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        Button(
            onClick = { isSheetVisible = !isSheetVisible },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Открыть/Закрыть TopSheet")
        }
        CustomTopSheet(isVisible = isSheetVisible)
    }
}
