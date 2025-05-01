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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.ortin.flightradar.R
import com.ortin.flightradar.presentation.component.flyoutbutton.FlyoutButton
import com.ortin.flightradar.presentation.component.flyoutbutton.FlyoutButtonItem
import com.ortin.flightradar.presentation.component.flyoutbutton.FlyoutButtonStack
import com.ortin.flightradar.presentation.component.mylocationbutton.MyLocationButton
import com.ortin.flightradar.presentation.util.clickableWithoutIndication
import com.ortin.flightradar.presentation.viewmodel.MapScreenState
import com.ortin.flightradar.presentation.viewmodel.MapScreenViewModel
import com.ortin.flightradar.ui.theme.OnBackground
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.TextStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.runtime.image.ImageProvider
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val mapView = remember { MapView(context) }

    val activity = context as ViewModelStoreOwner
    val viewModel: MapScreenViewModel = koinViewModel(viewModelStoreOwner = activity)
    val userLocation = viewModel.location.value
    var userLocationLayer: UserLocationLayer? = null

    val uiState = viewModel.mapScreenState.value.uiState
    val mapScreenState = viewModel.mapScreenState.value
    val isFlyoutVisible = mapScreenState is MapScreenState.FlyoutButtonVisible

    val buttons: List<FlyoutButtonItem> = listOf(
        FlyoutButtonItem.FAQ,
        FlyoutButtonItem.Feedback,
        FlyoutButtonItem.Information,
    )

    val localWidth = LocalConfiguration.current.screenWidthDp
    val flyoutButtonOffsetX by animateDpAsState(
        targetValue = if (uiState.isSideButtonsVisible) 0.dp else localWidth.dp,
        animationSpec = tween(durationMillis = 400),
        label = "FlyoutButtonStack offset"
    )
    val myLocationButtonOffsetX by animateDpAsState(
        targetValue = if (uiState.isSideButtonsVisible) 0.dp else -localWidth.dp,
        animationSpec = tween(durationMillis = 400),
        label = "MyLocationButton offset"
    )

    val cameraPosition = remember {
        mutableStateOf(
            CameraPosition(
                Point(55.699402, 37.625485),
                17.0f,
                0f,
                0f
            )
        )
    }

    /**
     * Это замоканные данные об аэропортах Москвы,
     * в дальнейшем, они будут получены с бэкенда
     **/
    val pinsCollection = mapView.mapWindow.map.mapObjects.addCollection()
    val points = listOf(
        Point(55.9761317, 37.410052) to "Шереметьево",
        Point(55.605787, 37.277518) to "Внуково",
        Point(55.50773, 37.50635) to "Остафьево",
        Point(55.406111, 37.908056) to "Домодедово",
        Point(55.561365, 38.137674) to "Жуковский",
    )

    val imageProvider = ImageProvider.fromResource(context, R.drawable.location_on)

    points.forEach { point ->
        pinsCollection.addPlacemark().apply {
            geometry = point.first
            setIcon(imageProvider)
            setText(
                point.second,
                TextStyle().apply {
                    size = 10f
                    placement = TextStyle.Placement.BOTTOM
                },
            )
        }
    }

    val inputListener = remember {
        object : InputListener {
            override fun onMapTap(map: Map, point: Point) {
                viewModel.changeMapScreenState()
            }

            override fun onMapLongTap(p0: Map, p1: Point) {
                /* do nothing */
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        mapView.mapWindow.map.addInputListener(inputListener)
        mapView.mapWindow.map.move(
            cameraPosition.value
        )
        MapKitFactory.getInstance().onStart()
        mapView.onStart()

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                MapKitFactory.getInstance().onStart()
                mapView.onStart()

                if (userLocationLayer == null) {
                    userLocationLayer =
                        MapKitFactory.getInstance().createUserLocationLayer(mapView.mapWindow)
                            .apply {
                                isVisible = true
                                isHeadingEnabled = true
                            }
                }
            } else if (event == Lifecycle.Event.ON_STOP) {
                mapView.onStop()
                MapKitFactory.getInstance().onStop()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            mapView.mapWindow.map.removeInputListener(inputListener)
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { mapView }
        )
        userLocation?.let {
            MyLocationButton(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            myLocationButtonOffsetX
                                .toPx()
                                .toInt(), 0
                        )
                    }
                    .padding(bottom = 50.dp)
                    .align(Alignment.BottomStart)
                    .padding(32.dp),
                mapView = mapView,
                userLocation = it,
                cameraPosition = cameraPosition
            )
        }
        if (uiState.isBackgroundDark) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OnBackground)
                    .clickableWithoutIndication {
                        viewModel.toggleFlyout()
                    }
            )
        }
        FlyoutButtonStack(
            modifier = Modifier
                .offset {
                    IntOffset(
                        flyoutButtonOffsetX
                            .toPx()
                            .toInt(), 0
                    )
                }
                .padding(bottom = 50.dp)
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            onClick = { viewModel.toggleFlyout() },
            isFlyoutVisible = isFlyoutVisible
        ) {
            Column(horizontalAlignment = Alignment.End) {
                buttons.forEach { item: FlyoutButtonItem ->
                    FlyoutButton(
                        onClick = {
                            navController.navigate(item.route)
                        },
                        title = item.title,
                        icon = item.icon
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
