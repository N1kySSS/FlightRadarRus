package com.ortin.flightradar.presentation.component.flyoutbutton

import com.ortin.flightradar.R

sealed class FlyoutButtonItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    //Add routes

    data object FAQ : FlyoutButtonItem(
        route = "",
        title = "FAQ",
        icon = R.drawable.question
    )

    data object Feedback : FlyoutButtonItem(
        route = "",
        title = "Обратная связь",
        icon = R.drawable.message
    )

    data object Information : FlyoutButtonItem(
        route = "",
        title = "О нас",
        icon = R.drawable.information
    )
}
