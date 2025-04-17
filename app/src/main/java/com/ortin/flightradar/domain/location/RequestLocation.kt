package com.ortin.flightradar.domain.location

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import com.ortin.flightradar.MainActivity
import com.ortin.flightradar.presentation.viewmodel.MapScreenViewModel

@Composable
fun requestLocationPermission(context: Context, viewModel: MapScreenViewModel, myLocationUtil: MyLocationUtil) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestMultiplePermissions(),
    onResult = { permissions ->
        if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            && permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        ) {
            myLocationUtil.requestLocationUpdates(viewModel = viewModel)
        } else {
            val showRequest = ActivityCompat.shouldShowRequestPermissionRationale(
                context as MainActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )

            if (!showRequest) {
                Toast.makeText(
                    context,
                    "You can enable your location later from android settings",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
)
