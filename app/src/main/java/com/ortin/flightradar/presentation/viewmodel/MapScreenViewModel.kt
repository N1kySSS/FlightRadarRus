package com.ortin.flightradar.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ortin.flightradar.data.bottomsheet.SheetContent
import com.ortin.flightradar.data.location.LocationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapScreenViewModel : ViewModel() {

    private val _activeSheet = mutableStateOf<SheetContent?>(null)
    val activeSheet: State<SheetContent?> = _activeSheet

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    private val _location = mutableStateOf<LocationData?>(null)
    val location = _location

    private val _mapScreenState = mutableStateOf<MapScreenState>(MapScreenState.Default)
    val mapScreenState: State<MapScreenState> = _mapScreenState

    fun showBottomSheet() {
        _mapScreenState.value = when (_mapScreenState.value) {
            is MapScreenState.BottomSheetVisible -> MapScreenState.Default
            else -> MapScreenState.BottomSheetVisible
        }
    }

    fun changeActiveSheet(sheet: SheetContent?) {
        _activeSheet.value = sheet
    }

    fun onTextChange(text: String) {
        _text.value = text.trimStart()
    }

    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation
    }

    fun changeMapScreenState() {
        _mapScreenState.value = when (_mapScreenState.value) {
            is MapScreenState.Default -> MapScreenState.Hidden
            else -> MapScreenState.Default
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
            isTopSheetVisible = false,
            isSideButtonsVisible = true,
            isBackgroundDark = false,
            isBottomSheetVisible = false
        )
    )

    data object Hidden : MapScreenState(
        UiState(
            isInterfaceVisible = false,
            isTopSheetVisible = false,
            isSideButtonsVisible = false,
            isBackgroundDark = false,
            isBottomSheetVisible = false
        )
    )

    data object TopSheetVisible : MapScreenState(
        UiState(
            isInterfaceVisible = true,
            isTopSheetVisible = true,
            isSideButtonsVisible = false,
            isBackgroundDark = false,
            isBottomSheetVisible = false
        )
    )

    data object BottomSheetVisible : MapScreenState(
        UiState(
            isInterfaceVisible = true,
            isTopSheetVisible = false,
            isSideButtonsVisible = true,
            isBackgroundDark = false,
            isBottomSheetVisible = true
        )
    )

    data object FlyoutButtonVisible : MapScreenState(
        UiState(
            isInterfaceVisible = true,
            isTopSheetVisible = false,
            isSideButtonsVisible = true,
            isBackgroundDark = true,
            isBottomSheetVisible = false
        )
    )
}

data class UiState(
    val isInterfaceVisible: Boolean,
    val isTopSheetVisible: Boolean,
    val isSideButtonsVisible: Boolean,
    val isBackgroundDark: Boolean,
    val isBottomSheetVisible: Boolean
)
