package com.ortin.flightradar.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ortin.flightradar.data.location.LocationData

class MapScreenViewModel : ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location = _location

    private val _mapScreenState = mutableStateOf<MapScreenState>(MapScreenState.Default)
    val mapScreenState: State<MapScreenState> = _mapScreenState

    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation
    }

    fun changeMapScreenState() {
        _mapScreenState.value = when (_mapScreenState.value) {
            is MapScreenState.Default -> MapScreenState.Hidden
            is MapScreenState.Hidden -> MapScreenState.Default
            is MapScreenState.TopSheetVisible -> MapScreenState.Default
            is MapScreenState.FlyoutButtonVisible -> MapScreenState.Default
        }
    }

    fun showTopSheet() {
        _mapScreenState.value = when (_mapScreenState.value) {
            is MapScreenState.TopSheetVisible -> MapScreenState.Default
            else -> MapScreenState.TopSheetVisible
        }
    }

    fun toggleFlyout() {
        _mapScreenState.value = when (_mapScreenState.value) {
            is MapScreenState.FlyoutButtonVisible -> MapScreenState.Default
            else -> MapScreenState.FlyoutButtonVisible
        }
    }
}

sealed class MapScreenState(val uiState: UiState) {

    data object Default : MapScreenState(
        UiState(
            isInterfaceVisible = true,
            isSheetVisible = false,
            isSideButtonsVisible = true,
            isBackgroundDark = false
        )
    )

    data object Hidden : MapScreenState(
        UiState(
            isInterfaceVisible = false,
            isSheetVisible = false,
            isSideButtonsVisible = false,
            isBackgroundDark = false
        )
    )

    data object TopSheetVisible : MapScreenState(
        UiState(
            isInterfaceVisible = true,
            isSheetVisible = true,
            isSideButtonsVisible = false,
            isBackgroundDark = false
        )
    )

    data object FlyoutButtonVisible : MapScreenState(
        UiState(
            isInterfaceVisible = true,
            isSheetVisible = false,
            isSideButtonsVisible = true,
            isBackgroundDark = true
        )
    )
}

data class UiState(
    val isInterfaceVisible: Boolean,
    val isSheetVisible: Boolean,
    val isSideButtonsVisible: Boolean,
    val isBackgroundDark: Boolean
)
