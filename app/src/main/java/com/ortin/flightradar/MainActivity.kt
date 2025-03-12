package com.ortin.flightradar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.ortin.flightradar.ui.theme.FlightRadarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlightRadarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val markerState = rememberMarkerState(position = LatLng(55.751244, 37.618423))
    val counter = remember { mutableIntStateOf(0) }
    val mapProperties = remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.HYBRID,
                minZoomPreference = 1.0f
            )
        )
    }

    LaunchedEffect(counter.intValue) {
        mapProperties.value = when (counter.intValue) {
            0 -> MapProperties(mapType = MapType.HYBRID, minZoomPreference = 1.0f)
            1 -> MapProperties(mapType = MapType.NORMAL, minZoomPreference = 1.0f)
            else -> MapProperties(mapType = MapType.SATELLITE, minZoomPreference = 1.0f)
        }
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        properties = MapProperties(mapType = MapType.NORMAL),
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(markerState.position, 12f)
        },
        onMapClick = {
            counter.intValue = (counter.intValue + 1) % 3
        }
    ) {
        MarkerInfoWindowContent(
            state = markerState,
            icon = remember { fromSvgToBitmap(context, R.drawable.airplane) },
        ) {
            Row(
                modifier = Modifier
                    .background(Color.Unspecified)
                    .padding(8.dp)
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo"
                )
                Text(text = "Рейс SU123", fontWeight = FontWeight.Bold)
            }
        }
        markerState.showInfoWindow()

        Marker(
            state = markerState,
            icon = remember { fromSvgToBitmap(context, R.drawable.airplane_yellow) },
            title = "Желтый самолет svg",
            snippet = "PLANE",
            rotation = 45f
        )
        Marker(
            state = rememberMarkerState(position = LatLng(55.761244, 37.628423)),
            icon = remember { fromSvgToBitmap(context, R.drawable.airplane_1) },
            title = "Самолет №1 svg"
        )
        Marker(
            state = rememberMarkerState(position = LatLng(55.771244, 37.628423)),
            icon = remember { fromSvgToBitmap(context, R.drawable.airplane_2) },
            title = "Самолет №2 svg"
        )
        Marker(
            state = rememberMarkerState(position = LatLng(55.771244, 37.638423)),
            icon = remember { fromSvgToBitmap(context, R.drawable.airplane_3) },
            title = "Самолет №3 svg"
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
