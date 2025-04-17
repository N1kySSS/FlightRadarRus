package com.ortin.flightradar.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ortin.flightradar.data.location.LocationData

class MapScreenViewModel : ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location = _location

    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation
    }
}

sealed class MapScreenState {

    data class Hidden(val uiState: MutableState<Boolean>) : MapScreenState()

    data class TopSheetVisible(val uiState: MutableState<Boolean>) : MapScreenState()

    data class FlyoutButtonVisible(val uiState: MutableState<Boolean>) : MapScreenState()
}
