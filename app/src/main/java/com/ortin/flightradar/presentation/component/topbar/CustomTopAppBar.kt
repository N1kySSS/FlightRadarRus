package com.ortin.flightradar.presentation.component.topbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ortin.flightradar.R

@Composable
fun CustomTopAppBar(
    value: String,
    isSheetVisible: Boolean,
    isIconEnable: Boolean,
    onIconClick: () -> Unit,
    onValueChanged: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isSheetVisible) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "Rotation"
    )

    Row(
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth()
            .background(color = Color.Black.copy(alpha = 0.4f))
            .padding(start = 16.dp, end = 16.dp, top = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            modifier = Modifier
                .clickable(
                    enabled = isIconEnable,
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onIconClick()
                }
                .rotate(rotationAngle),
            painter = painterResource(R.drawable.arrow_down),
            contentDescription = "More info",
            tint = Color.White
        )
        CustomSearchBar(
            onValueChanged = onValueChanged,
            value = value,
            modifier = Modifier.weight(1f)
        )
        Icon(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    TODO("Add action")
                },
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile",
            tint = Color.White
        )
    }
}
