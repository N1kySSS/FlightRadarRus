package com.ortin.flightradar.presentation.screen

import android.widget.Toast
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
import com.ortin.flightradar.presentation.component.mylocationbutton.MyLocationButton
import com.ortin.flightradar.presentation.util.clickableWithoutIndication
import com.ortin.flightradar.presentation.viewmodel.LocationViewModel
import com.ortin.flightradar.ui.theme.OnBackground
import org.koin.androidx.compose.koinViewModel
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition
import org.ramani.compose.MapLibre
import org.ramani.compose.Symbol
import org.ramani.compose.UiSettings
import org.ramani.compose.rememberMapViewWithLifecycle

@Composable
fun MapScreen(isFlyoutButtonVisible: Boolean) {
    val context = LocalContext.current
    val key = context.getString(R.string.MAPS_API_KEY)

    val locationVM: LocationViewModel = koinViewModel()
    val userLocation = locationVM.location

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

    val mapUiSettings = UiSettings(
        isLogoEnabled = false,
        isAttributionEnabled = false,
        rotateGesturesEnabled = false
    )
    val cameraPosition = remember {
        mutableStateOf(
            CameraPosition(
                target = LatLng(55.699402, 37.625485),
                zoom = 17.0
            )
        )
    }

    var styleState by remember { mutableStateOf(false) }
    val mapView = rememberMapViewWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        MapLibre(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            uiSettings = mapUiSettings,
            cameraPosition = cameraPosition.value,
            onMapClick = { if (isClickEnable.value) MainActivity.onMapClicked() },
            onStyleLoaded = { style ->
                styleState = style.isFullyLoaded
            },
            mapView = mapView,
            styleBuilder = Style.Builder()
                .fromUri("https://api.maptiler.com/maps/streets-v2/style.json?key=$key"),
        ) {
            if (styleState) {
                Symbol(
                    center = LatLng(55.699402, 37.625485),
                    text = "ЗИТ Офис",
                    onClick = {
                        Toast
                            .makeText(context, "Это главный офис ФГУП ЗИТ", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
        if (isBackgroundDark) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OnBackground)
                    .clickableWithoutIndication(
                        onClick = {
                            /* do nothing */
                        }
                    )
            )
        }
        FlyoutButtonStack(
            modifier = Modifier
                .offset {
                    IntOffset(
                        offsetX
                            .toPx()
                            .toInt(), 0
                    )
                }
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
        userLocation.value?.let {
            MyLocationButton(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .align(Alignment.BottomStart)
                    .padding(32.dp),
                userLocation = it,
                cameraPosition = cameraPosition
            )
        }
    }
}
