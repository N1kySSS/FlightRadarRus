package com.ortin.flightradar.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ortin.flightradar.data.location.LocationData

class LocationViewModel : ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location = _location

    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation
    }
}
