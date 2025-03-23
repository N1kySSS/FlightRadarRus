package com.ortin.flightradar

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.ortin.flightradar.presentation.component.navbar.CustomBottomNavBar
import com.ortin.flightradar.presentation.component.topbar.CustomTopAppBar
import com.ortin.flightradar.presentation.component.topsheet.CustomTopSheet
import com.ortin.flightradar.presentation.screen.MapScreen
import com.ortin.flightradar.ui.theme.FlightRadarTheme

class MainActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    companion object {
        val isInterfaceVisible = mutableStateOf(true)

        fun onMapClicked() {
            isInterfaceVisible.value = !isInterfaceVisible.value
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FlightRadarTheme {
                var isSheetVisible by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AnimatedVisibility(
                            visible = isInterfaceVisible.value,
                            exit = slideOutVertically(animationSpec = tween(200)) { -it },
                            enter = slideInVertically(animationSpec = tween(340)) { -it }
                        ) {
                            CustomTopSheet(
                                isVisible = isSheetVisible,
                                content = { /* Content will be added later */ }
                            )
                            CustomTopAppBar(
                                value = "",
                                isSheetVisible = isSheetVisible,
                                onIconClick = { isSheetVisible = !isSheetVisible },
                                onValueChanged = {}
                            )
                        }
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isInterfaceVisible.value,
                            exit = slideOutVertically(animationSpec = tween(200)) { it },
                            enter = slideInVertically(animationSpec = tween(200)) { it }
                        ) {
                            CustomBottomNavBar()
                        }
                    }
                ) { innerPadding ->
                    MapScreen(Modifier)
                }
            }
        }
    }
}
