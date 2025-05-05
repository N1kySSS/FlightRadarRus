package com.ortin.flightradar.presentation.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBottomSheet() {
    /**
     * Image's border color and text on image background color
     * will be changed later, based on the state
     **/
    val sheetState = rememberModalBottomSheetState()
    val showBottomSheet = remember { mutableStateOf(false) }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetMaxHeight = screenHeight / 2

    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet.value = false
        },
        sheetState = sheetState,
        scrimColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .heightIn(max = sheetMaxHeight)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "ТИП КАРТЫ",
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
                Box(modifier = Modifier.wrapContentSize()) {
                    Image(
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.normal),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.Center,
                        contentDescription = "Normal map view"
                    )
                    Text(
                        modifier = Modifier
                            .width(125.dp)
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
                        text = "Стандартный",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
                Box(modifier = Modifier.wrapContentSize()) {
                    Image(
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.satellite),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.Center,
                        contentDescription = "Satellite map view"
                    )
                    Text(
                        modifier = Modifier
                            .width(125.dp)
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
                        text = "Спутник",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
                Box(modifier = Modifier.wrapContentSize()) {
                    Image(
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.hybrid),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.Center,
                        contentDescription = "Hybrid map view"
                    )
                    Text(
                        modifier = Modifier
                            .width(125.dp)
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
                        text = "Гибрид",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 10.dp),
                thickness = 2.dp
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "МЕТКИ ВОЗДУШНЫХ СУДОВ",
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
                Box(modifier = Modifier.wrapContentSize()) {
                    Image(
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.off_mark_state),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.Center,
                        contentDescription = "Off marks"
                    )
                    Text(
                        modifier = Modifier
                            .width(125.dp)
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
                Box(modifier = Modifier.wrapContentSize()) {
                    Image(
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.logo_mark_state),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.Center,
                        contentDescription = "Show company logo"
                    )
                    Text(
                        modifier = Modifier
                            .width(125.dp)
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
                        text = "Логотип",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
                Box(modifier = Modifier.wrapContentSize()) {
                    Image(
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.text_mark_state),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.Center,
                        contentDescription = "Show text"
                    )
                    Text(
                        modifier = Modifier
                            .width(125.dp)
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
                        text = "Текст",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 10.dp),
                thickness = 2.dp
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "ЗНАЧКИ АЭРОПОРТОВ",
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
                        painter = painterResource(R.drawable.show_airports),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        contentDescription = "Show text"
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
                    text = "Отображать расположение аэропортов на карте. " +
                            "Измените масштаб карты, чтобы увидеть больше аэропортов.",
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
                text = "МОЁ МЕСТОПОЛОЖЕНИЕ",
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
                        painter = painterResource(R.drawable.show_user_location),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        contentDescription = "Show text"
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
                    text = "Отображать ваше расположение как точку на карте.",
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
    }
}

@Preview
@Composable
fun SettingsBottomSheetPreview() {
    SettingsBottomSheet()
}
