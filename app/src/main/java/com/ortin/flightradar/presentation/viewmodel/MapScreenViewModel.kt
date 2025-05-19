package com.ortin.flightradar.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ortin.flightradar.R
import com.ortin.flightradar.data.bottomsheet.SheetContent
import com.ortin.flightradar.data.location.LocationData
import com.yandex.mapkit.map.MapType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapScreenViewModel : ViewModel() {

    val mapTypes = listOf(
        R.drawable.map_type_none to "Нет",
        R.drawable.map_type_normal to "Стандартный",
        R.drawable.map_type_vector to "Векторный"
    )

    private val _selectedMapType = mutableStateOf(mapTypes[1].second)
    val selectedMapType = _selectedMapType

    val markTypes = listOf(
        R.drawable.mark_state_off to "Выкл.",
        R.drawable.mark_state_logo to "Логотип",
        R.drawable.mark_state_text to "Текст"
    )

    private val _selectedMarkType = mutableStateOf<String?>(markTypes[0].second)
    val selectedMarkType = _selectedMarkType

    private val mapTypeYandex = mapOf(
        "Нет" to MapType.NONE,
        "Стандартный" to MapType.MAP,
        "Векторный" to MapType.VECTOR_MAP
    )

    private val _activeMapType = mutableStateOf(MapType.MAP)
    val activeMapType = _activeMapType

    private val _isAirportsVisible = mutableStateOf(true)
    val isAirportsVisible = _isAirportsVisible

    private val _isMyLocationVisible = mutableStateOf(true)
    val isMyLocationVisible = _isMyLocationVisible

    private val _isCurrentWeatherDisplay = mutableStateOf(false)
    val isCurrentWeatherDisplay = _isCurrentWeatherDisplay

    private val _isCloudCoverDisplay = mutableStateOf(false)
    val isCloudCoverDisplay = _isCloudCoverDisplay

    private val _isTotalPrecipitationDisplay = mutableStateOf(false)
    val isTotalPrecipitationDisplay = _isTotalPrecipitationDisplay

    private val _isPrecipitationIntensityDisplay = mutableStateOf(false)
    val isPrecipitationIntensityDisplay = _isPrecipitationIntensityDisplay

    fun onCurrentWeatherClicked() {
        _isCurrentWeatherDisplay.value = !_isCurrentWeatherDisplay.value
    }

    fun onCloudCoverClicked() {
        _isCloudCoverDisplay.value = !_isCloudCoverDisplay.value
    }

    fun onTotalPrecipitationClicked() {
        _isTotalPrecipitationDisplay.value = !_isTotalPrecipitationDisplay.value
    }

    fun onPrecipitationIntensityClicked() {
        _isPrecipitationIntensityDisplay.value = !_isPrecipitationIntensityDisplay.value
    }

    fun onMapTypeClicked(type: String) {
        _selectedMapType.value = type
        _activeMapType.value = mapTypeYandex[_selectedMapType.value] ?: MapType.MAP
    }

    fun onMarkTypeClicked(type: String) {
        _selectedMarkType.value = type
    }

    fun onAirportVisibleSettingsClicked() {
        _isAirportsVisible.value = !_isAirportsVisible.value
    }

    fun onMyLocationVisibleSettingsClicked() {
        _isMyLocationVisible.value = !_isMyLocationVisible.value
    }

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    private val _location = mutableStateOf<LocationData?>(null)
    val location = _location

    private val _mapScreenState = mutableStateOf<MapScreenState>(MapScreenState.Default)
    val mapScreenState: State<MapScreenState> = _mapScreenState

    private val _activeSheet = mutableStateOf<SheetContent?>(null)
    val activeSheet: State<SheetContent?> = _activeSheet

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

    fun showBottomSheet() {
        _mapScreenState.value = when (_mapScreenState.value) {
            is MapScreenState.BottomSheetVisible -> MapScreenState.Default
            else -> MapScreenState.BottomSheetVisible
        }
    }

    fun changeActiveSheet(sheet: SheetContent?) {
        _activeSheet.value = sheet
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
