package com.ortin.flightradar.presentation.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.ortin.flightradar.R

@Composable
fun MapScreen(isMyLocationEnabled: Boolean, padding: PaddingValues) {
    val context = LocalContext.current
    val isLocationEnabledState = rememberUpdatedState(isMyLocationEnabled)
    val myLocationState = rememberMarkerState(position = LatLng(55.751244, 37.618423))
    val isUiVisible = remember { mutableStateOf(true) }

    val markerPlaneState = rememberMarkerState(position = LatLng(55.761244, 37.618423))
    val yellowPlaneState = rememberMarkerState(position = LatLng(55.761244, 37.628423))
    val firstPlaneState = rememberMarkerState(position = LatLng(55.761244, 37.638423))
    val secondPlaneState = rememberMarkerState(position = LatLng(55.771244, 37.618423))
    val thirdPlaneState = rememberMarkerState(position = LatLng(55.771244, 37.628423))
    val customPlaneState = rememberMarkerState(position = LatLng(55.771244, 37.638423))
    val customPlaneSVGState = rememberMarkerState(position = LatLng(55.771244, 37.648423))

    val markerPlane = remember { fromSvgToBitmap(context, R.drawable.airplane) }
    val yellowPlane = remember { fromSvgToBitmap(context, R.drawable.airplane_yellow) }
    val firstPlane = remember { fromSvgToBitmap(context, R.drawable.airplane_1) }
    val secondPlane = remember { fromSvgToBitmap(context, R.drawable.airplane_2) }
    val thirdPlane = remember { fromSvgToBitmap(context, R.drawable.airplane_3) }
    val customPlane = remember { fromSvgToBitmap(context, R.drawable.custom_plane_svg) }

    GoogleMap(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        properties = MapProperties(minZoomPreference = 5f, isMyLocationEnabled = isLocationEnabledState.value),
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(myLocationState.position, 15f)
        },
        uiSettings = MapUiSettings(mapToolbarEnabled = isUiVisible.value),
        onMapClick = {
            isUiVisible.value = false
        }
    ) {
        MarkerInfoWindow(
            state = markerPlaneState,
            icon = markerPlane,
        ) {
            Row(
                modifier = Modifier
                    .background(Color.Unspecified)
                    .padding(8.dp)
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo"
                )
                Text(text = "Рейс SU123", fontWeight = FontWeight.Bold)
            }
        }
        markerPlaneState.showInfoWindow()

        Marker(
            state = yellowPlaneState,
            icon = yellowPlane,
            title = "Желтый самолет svg",
            snippet = "PLANE",
            rotation = 45f
        )
        Marker(
            state = firstPlaneState,
            icon = firstPlane,
            title = "Самолет №1 svg"
        )
        Marker(
            state = secondPlaneState,
            icon = secondPlane,
            title = "Самолет №2 svg"
        )
        Marker(
            state = thirdPlaneState,
            icon = thirdPlane,
            title = "Самолет №3 svg"
        )
        Marker(
            state = customPlaneState,
            icon = BitmapDescriptorFactory.fromResource(R.drawable.custom_plane),
            title = "Кастомный самолет png"
        )
        Marker(
            state = customPlaneSVGState,
            icon = customPlane,
            title = "Кастомный самолет sng"
        )
    }
}

fun fromSvgToBitmap(context: Context, resId: Int): BitmapDescriptor {
    val drawable = context.getDrawable(resId)!!
    val bitmap = Bitmap.createBitmap(
        drawable.minimumWidth,
        drawable.minimumHeight,
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return BitmapDescriptorFactory.fromBitmap(bitmap)
}
