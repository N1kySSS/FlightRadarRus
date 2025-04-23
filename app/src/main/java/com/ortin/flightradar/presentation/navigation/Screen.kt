package com.ortin.flightradar.presentation.navigation

sealed class Screen(val route: String) {
    data object MapScreen : Screen("map_screen")

    data object FAQScreen : Screen("faq_screen")

    data object FeedbackScreen : Screen("feedback_screen")

    data object InformationScreen : Screen("information_screen")
}
