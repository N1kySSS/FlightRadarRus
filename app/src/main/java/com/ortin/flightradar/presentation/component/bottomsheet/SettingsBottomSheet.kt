package com.ortin.flightradar.presentation.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.getValue
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
import com.ortin.flightradar.presentation.util.clickableWithoutIndication
import com.ortin.flightradar.presentation.viewmodel.MapScreenViewModel
import com.ortin.flightradar.ui.theme.Selected

@Composable
fun SettingsBottomSheet(viewModel: MapScreenViewModel) {
    val selectedMapType by viewModel.selectedMapType
    val selectedMarkType by viewModel.selectedMarkType
    val isAirportsVisible by viewModel.isAirportsVisible
    val isMyLocationVisible by viewModel.isMyLocationVisible

    val mapTypes = viewModel.mapTypes
    val markTypes = viewModel.markTypes

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
        mapTypes.forEach { (drawable, type) ->
            val isSelected = selectedMapType.equals(type)

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clickableWithoutIndication {
                        if (!isSelected) {
                            viewModel.onMapTypeClicked(type)
                        }
                    }
                    .border(
                        width = 2.dp,
                        color = if (isSelected) Selected else Color.Transparent,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                Image(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .align(Alignment.Center),
                    painter = painterResource(drawable),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    contentDescription = "Вид карты - $type"
                )
                Text(
                    modifier = Modifier
                        .width(125.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            color = if (isSelected) Selected else Color.Gray.copy(alpha = 0.65f),
                            shape = RoundedCornerShape(
                                bottomStart = 4.dp,
                                bottomEnd = 4.dp
                            )
                        ),
                    text = type,
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
        markTypes.forEach { (drawable, type) ->
            val isSelected = selectedMarkType.equals(type)

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clickableWithoutIndication {
                        if (!isSelected) {
                            viewModel.onMarkTypeClicked(type)
                        }
                    }
                    .border(
                        width = 3.dp,
                        color = if (isSelected) Selected else Color.Transparent,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                Image(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .align(Alignment.Center),
                    painter = painterResource(drawable),
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.Center,
                    contentDescription = "Метка - $type"
                )
                Text(
                    modifier = Modifier
                        .width(125.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            color = if (isSelected) Selected else Color.Gray.copy(alpha = 0.65f),
                            shape = RoundedCornerShape(
                                bottomStart = 4.dp,
                                bottomEnd = 4.dp
                            )
                        ),
                    text = type,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    ),
                )
            }
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
                .clickableWithoutIndication {
                    viewModel.onAirportVisibleSettingsClicked()
                }
                .border(
                    width = 3.dp,
                    color = if (isAirportsVisible) Selected else Color.Transparent,
                    shape = RoundedCornerShape(4.dp)
                )
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
                        color = if (isAirportsVisible) Selected else Color.Gray.copy(alpha = 0.65f),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp
                        )
                    ),
                text = if (isAirportsVisible) "Вкл." else "Выкл.",
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
                .clickableWithoutIndication {
                    viewModel.onMyLocationVisibleSettingsClicked()
                }
                .border(
                    width = 3.dp,
                    color = if (isMyLocationVisible) Selected else Color.Transparent,
                    shape = RoundedCornerShape(4.dp)
                )
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
                        color = if (isMyLocationVisible) Selected else Color.Gray.copy(alpha = 0.65f),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp
                        )
                    ),
                text = if (isMyLocationVisible) "Вкл." else "Выкл.",
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
