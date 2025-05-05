package com.ortin.flightradar.presentation.component.navbar

import com.ortin.flightradar.R
import com.ortin.flightradar.data.bottomsheet.SheetContent

sealed class NavigationBarItem(
    val title: String,
    val icon: Int,
    val sheetContent: SheetContent?
) {
    data object Settings : NavigationBarItem(
        title = "Настройки",
        icon = R.drawable.settings,
        sheetContent = SheetContent.SETTINGS
    )

    data object Weather : NavigationBarItem(
        title = "Погода",
        icon = R.drawable.weather,
        sheetContent = SheetContent.WEATHER
    )

    data object Filters : NavigationBarItem(
        title = "Фильтры",
        icon = R.drawable.filters,
        sheetContent = SheetContent.FILTERS
    )

    data object AR : NavigationBarItem(
        title = "Камера",
        icon = R.drawable.ar,
        sheetContent = null
    )
}
