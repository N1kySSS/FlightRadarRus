package com.ortin.flightradar.presentation.component.flyoutbutton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.ortin.flightradar.MainActivity.Companion.isClickEnable
import com.ortin.flightradar.R
import com.ortin.flightradar.ui.theme.Primary

fun interface FlyoutScope {

    fun toggle()
}

@Composable
fun BoxScope.FlyoutButtonStack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable FlyoutScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isVisible by remember { mutableStateOf(false) }
    val scope = remember {
        FlyoutScope {
            isVisible = !isVisible
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        AnimatedVisibility(visible = isVisible) {
            Column {
                scope.content()
            }
        }
        Icon(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.4f),
                    shape = CircleShape
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    // Maybe add hidden FlyoutButtons and darkBox after map clicked
                    isClickEnable.value = !isClickEnable.value
                    onClick()
                    scope.toggle()
                },
            painter = painterResource(R.drawable.more),
            contentDescription = "Profile",
            tint = if (isVisible) Primary else Color.White
        )
    }
}

@Composable
fun FlyoutScope.FlyoutButton(
    route: String,
    title: String,
    icon: Int
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { /* TODO(Add action) */ },
            text = title,
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.W300,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
            ),
        )
        Spacer(Modifier.width(4.dp))
        Icon(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.4f),
                    shape = CircleShape
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { /* TODO(Add action) */ },
            painter = painterResource(icon),
            contentDescription = title,
            tint = Color.White
        )
    }
}


@Preview
@Composable
fun FlyoutButtonPreview() {
    val buttons: List<FlyoutButtonItem> = listOf(
        FlyoutButtonItem.FAQ,
        FlyoutButtonItem.Feedback,
        FlyoutButtonItem.Information,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        FlyoutButtonStack(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            onClick = {}
        ) {
            Column(horizontalAlignment = Alignment.End) {
                buttons.forEach { item: FlyoutButtonItem ->
                    FlyoutButton(
                        route = item.route,
                        title = item.title,
                        icon = item.icon
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
