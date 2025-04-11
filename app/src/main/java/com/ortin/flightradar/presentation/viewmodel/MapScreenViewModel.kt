package com.ortin.flightradar.presentation.viewmodel

import androidx.compose.runtime.MutableState

sealed class MapScreenState {

    data class Hidden(val uiState: MutableState<Boolean>) : MapScreenState()

    data class TopSheetVisible(val uiState: MutableState<Boolean>) : MapScreenState()

    data class FlyoutButtonVisible(val uiState: MutableState<Boolean>) : MapScreenState()
}
