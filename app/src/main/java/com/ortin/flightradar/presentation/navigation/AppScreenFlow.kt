package com.ortin.flightradar.presentation.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ortin.flightradar.presentation.screen.FAQScreen
import com.ortin.flightradar.presentation.screen.FeedbackScreen
import com.ortin.flightradar.presentation.screen.InformationScreen
import com.ortin.flightradar.presentation.screen.MapScreen

@Composable
fun AppScreenFlow(
    paddingValues: PaddingValues,
    navController: NavHostController,
    isSideButtonsVisible: MutableState<Boolean>
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MapScreen.route
    ) {
        composable(Screen.MapScreen.route) {
            MapScreen(
                navController = navController,
                isSideButtonsVisible = isSideButtonsVisible
            )
        }

        composable(
            route = Screen.FAQScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ) {
            FAQScreen {
                navController.popBackStack()
            }
        }

        composable(
            route = Screen.FeedbackScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ) {
            FeedbackScreen {
                navController.popBackStack()
            }
        }

        composable(
            route = Screen.InformationScreen.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ) {
            InformationScreen {
                navController.popBackStack()
            }
        }
    }
}
