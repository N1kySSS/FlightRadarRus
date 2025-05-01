package com.ortin.flightradar.presentation.component.topbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ortin.flightradar.R
import com.ortin.flightradar.presentation.component.topsheet.CustomTopSheet
import com.ortin.flightradar.presentation.util.clickableWithoutIndication
import com.ortin.flightradar.ui.theme.Background
import com.ortin.flightradar.ui.theme.Primary

@Composable
fun CustomTopAppBar(
    value: String,
    isSheetVisible: Boolean,
    onIconClick: () -> Unit,
    onValueChanged: (String) -> Unit
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isSheetVisible) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "Rotation"
    )

    Row(
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth()
            .background(color = Background)
            .padding(start = 16.dp, end = 16.dp, top = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            modifier = Modifier
                .clickableWithoutIndication {
                    onIconClick()
                }
                .rotate(rotationAngle),
            painter = painterResource(R.drawable.arrow_down),
            contentDescription = "More info",
            tint = Primary
        )
        CustomSearchBar(
            onValueChanged = onValueChanged,
            value = value,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(R.drawable.app_icon),
            contentDescription = "ЗИТ",
            tint = Primary
        )
    }
    CustomTopSheet(
        isVisible = isSheetVisible,
        content = {}
    )
}
