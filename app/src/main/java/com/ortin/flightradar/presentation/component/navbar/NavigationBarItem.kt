package com.ortin.flightradar.presentation.component.navbar

import com.ortin.flightradar.R

sealed class NavigationBarItem(
    val title: String,
    val icon: Int
) {
    data object Settings : NavigationBarItem(
        title = "Настройки",
        icon = R.drawable.settings
    )

    data object Weather : NavigationBarItem(
        title = "Погода",
        icon = R.drawable.weather
    )

    data object Filters : NavigationBarItem(
        title = "Фильтры",
        icon = R.drawable.filters
    )

    data object AR : NavigationBarItem(
        title = "Камера",
        icon = R.drawable.ar
    )
}
