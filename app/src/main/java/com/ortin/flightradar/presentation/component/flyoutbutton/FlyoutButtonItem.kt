package com.ortin.flightradar.presentation.component.flyoutbutton

import com.ortin.flightradar.R
import com.ortin.flightradar.presentation.navigation.Screen

sealed class FlyoutButtonItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object FAQ : FlyoutButtonItem(
        route = Screen.FAQScreen.route,
        title = "FAQ",
        icon = R.drawable.question
    )

    data object Feedback : FlyoutButtonItem(
        route = Screen.FeedbackScreen.route,
        title = "Обратная связь",
        icon = R.drawable.message
    )

    data object Information : FlyoutButtonItem(
        route = Screen.InformationScreen.route,
        title = "О нас",
        icon = R.drawable.information
    )
}
