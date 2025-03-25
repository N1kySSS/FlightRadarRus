package com.ortin.flightradar.presentation.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ortin.flightradar.MainActivity
import com.ortin.flightradar.MainActivity.Companion.isClickEnable
import com.ortin.flightradar.R
import com.ortin.flightradar.presentation.component.flyoutbutton.FlyoutButton
import com.ortin.flightradar.presentation.component.flyoutbutton.FlyoutButtonItem
import com.ortin.flightradar.presentation.component.flyoutbutton.FlyoutButtonStack
import org.maplibre.android.maps.Style
import org.ramani.compose.MapLibre

@Composable
fun MapScreen(isFlyoutButtonVisible: Boolean) {
    val context = LocalContext.current
    val key = context.getString(R.string.MAPS_API_KEY)

    val buttons: List<FlyoutButtonItem> = listOf(
        FlyoutButtonItem.FAQ,
        FlyoutButtonItem.Feedback,
        FlyoutButtonItem.Information,
    )

    val localWidth = LocalConfiguration.current.screenWidthDp
    val offsetX by animateDpAsState(
        targetValue = if (isFlyoutButtonVisible) 0.dp else localWidth.dp,
        animationSpec = tween(durationMillis = 400),
        label = "FlyoutButtonStack offset"
    )

    var isBackgroundDark by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        MapLibre(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            onMapClick = { if (isClickEnable.value) MainActivity.onMapClicked() },
            styleBuilder = Style.Builder()
                .fromUri("https://api.maptiler.com/maps/streets-v2/style.json?key=$key")
        )
        if (isBackgroundDark) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
            )
        }
        FlyoutButtonStack(
            modifier = Modifier
                .offset { IntOffset(offsetX.toPx().toInt(), 0) }
                .padding(bottom = 50.dp)
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            onClick = { isBackgroundDark = !isBackgroundDark }
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
