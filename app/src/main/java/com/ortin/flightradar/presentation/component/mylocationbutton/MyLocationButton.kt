package com.ortin.flightradar.presentation.component.mylocationbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ortin.flightradar.R
import com.ortin.flightradar.data.location.LocationData
import com.ortin.flightradar.ui.theme.Background
import com.ortin.flightradar.ui.theme.Primary
import org.maplibre.android.geometry.LatLng
import org.ramani.compose.CameraPosition

@Composable
fun MyLocationButton(
    modifier: Modifier,
    userLocation: LocationData,
    cameraPosition: MutableState<CameraPosition>
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(40.dp)
            .background(
                color = Background,
                shape = CircleShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                cameraPosition.value = CameraPosition(
                    target = LatLng(userLocation.latitude, userLocation.longitude),
                    zoom = 11.0
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
