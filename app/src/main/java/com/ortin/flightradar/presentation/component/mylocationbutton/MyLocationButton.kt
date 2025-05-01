package com.ortin.flightradar.presentation.component.mylocationbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ortin.flightradar.R
import com.ortin.flightradar.data.location.LocationData
import com.ortin.flightradar.presentation.util.clickableWithoutIndication
import com.ortin.flightradar.ui.theme.Background
import com.ortin.flightradar.ui.theme.Primary
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

@Composable
fun MyLocationButton(
    modifier: Modifier,
    mapView: MapView,
    userLocation: LocationData,
    cameraPosition: MutableState<CameraPosition>
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(
                color = Background,
                shape = CircleShape
            )
            .clickableWithoutIndication {
                cameraPosition.value = CameraPosition(
                    Point(userLocation.latitude, userLocation.longitude),
                    11.0f,
                    0f,
                    0f
                )
                mapView.mapWindow.map.move(
                    cameraPosition.value,
                    Animation(Animation.Type.LINEAR, 1.5f),
                    null
                )
            }
    ) {
        Icon(
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.Center),
            painter = painterResource(R.drawable.my_location),
            contentDescription = "My location",
            tint = Primary
        )
    }
}
