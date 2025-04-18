package com.ortin.flightradar

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.ortin.flightradar.domain.location.MyLocationUtil
import com.ortin.flightradar.domain.location.requestLocationPermission
import com.ortin.flightradar.presentation.component.navbar.CustomBottomNavBar
import com.ortin.flightradar.presentation.component.topbar.CustomTopAppBar
import com.ortin.flightradar.presentation.component.topsheet.CustomTopSheet
import com.ortin.flightradar.presentation.screen.MapScreen
import com.ortin.flightradar.presentation.viewmodel.MapScreenViewModel
import com.ortin.flightradar.ui.theme.FlightRadarTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    companion object {
        val isInterfaceVisible = mutableStateOf(true)
        val isSheetVisible = mutableStateOf(false)
        val isSideButtonsVisible = mutableStateOf(true)
        val isClickEnable = mutableStateOf(true)

        fun onMapClicked() {
            if (isSheetVisible.value) {
                isSheetVisible.value = !isSheetVisible.value
                isSideButtonsVisible.value = !isSideButtonsVisible.value
            } else {
                isInterfaceVisible.value = !isInterfaceVisible.value
                isSideButtonsVisible.value = !isSideButtonsVisible.value
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KoinContext {
                FlightRadarTheme {
                    val context = LocalContext.current
                    val myLocationUtil = MyLocationUtil(context)
                    val viewModel: MapScreenViewModel = koinViewModel()
                    val launcher = requestLocationPermission(
                        context = context,
                        viewModel = viewModel,
                        myLocationUtil = myLocationUtil
                    )

                    LaunchedEffect(Unit) {
                        launcher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                        )
                    }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            AnimatedVisibility(
                                visible = isInterfaceVisible.value,
                                exit = slideOutVertically(animationSpec = tween(400)) { -it },
                                enter = slideInVertically(animationSpec = tween(540)) { -it }
                            ) {
                                CustomTopSheet(
                                    isVisible = isSheetVisible.value,
                                    content = { /* Content will be added later */ }
                                )
                                CustomTopAppBar(
                                    value = "",
                                    isSheetVisible = isSheetVisible.value,
                                    onIconClick = {
                                        isSheetVisible.value = !isSheetVisible.value
                                        isSideButtonsVisible.value =
                                            !isSideButtonsVisible.value
                                    },
                                    onValueChanged = {},
                                    isIconEnable = isClickEnable.value
                                )
                            }
                        },
                        bottomBar = {
                            AnimatedVisibility(
                                visible = isInterfaceVisible.value,
                                exit = slideOutVertically(animationSpec = tween(400)) { it },
                                enter = slideInVertically(animationSpec = tween(400)) { it }
                            ) {
                                CustomBottomNavBar()
                            }
                        }
                    ) { innerPadding ->
                        MapScreen(isSideButtonsVisible.value)
                    }
                }
            }
        }
    }
}
