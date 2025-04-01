package com.ortin.flightradar.presentation.component.navbar

import com.ortin.flightradar.R

sealed class NavigationBarItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    //Add routes

    data object Settings : NavigationBarItem(
        route = "",
        title = "Настройки",
        icon = R.drawable.settings
    )

    data object Weather : NavigationBarItem(
        route = "",
        title = "Погода",
        icon = R.drawable.weather
    )

    data object Filters : NavigationBarItem(
        route = "",
        title = "Фильтры",
        icon = R.drawable.filters
    )

    data object AR : NavigationBarItem(
        route = "",
        title = "Камера",
        icon = R.drawable.ar
    )
}
