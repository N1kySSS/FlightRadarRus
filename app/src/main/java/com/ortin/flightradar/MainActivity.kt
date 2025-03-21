package com.ortin.flightradar

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.MapsInitializer
import com.ortin.flightradar.presentation.component.navbar.CustomBottomNavBar
import com.ortin.flightradar.presentation.component.topbar.CustomTopAppBar
import com.ortin.flightradar.presentation.screen.MapScreen
import com.ortin.flightradar.ui.theme.FlightRadarTheme

class MainActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    private var isMyLocationEnabled by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        checkLocationPermission()
        MapsInitializer.initialize(applicationContext)

        setContent {
            FlightRadarTheme {
                var isInterfaceVisible by remember { mutableStateOf(true) }
                val interactionSource = remember { MutableInteractionSource() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AnimatedVisibility(
                            visible = isInterfaceVisible,
                            exit = slideOutVertically(animationSpec = tween(200)) { -it },
                            enter = slideInVertically(animationSpec = tween(340)) { -it }
                        ) {
                            CustomTopAppBar({}, "")
                        }
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isInterfaceVisible,
                            exit = slideOutVertically(animationSpec = tween(200)) { it },
                            enter = slideInVertically(animationSpec = tween(200)) { it }
                        ) {
                            CustomBottomNavBar()
                        }
                    }
                ) { innerPadding ->
                    MapScreen(
                        Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { isInterfaceVisible = !isInterfaceVisible },
                        isMyLocationEnabled,
                        innerPadding
                    )
                }
            }
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1
            )
        } else {
            isMyLocationEnabled = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            isMyLocationEnabled = true
        }
    }
}
