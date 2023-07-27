package com.example.tiktok.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.authentication.onboarding.InterestRoute
import com.example.tiktok.ui.authentication.onboarding.TutorialRoute

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController,
) {
    navigation(
        route = TopLevelDestination.Onboarding.route,
        startDestination = OnboardingDestination.Interest.route
    ) {
        composable(
            route = OnboardingDestination.Interest.route
        ) {
            InterestRoute(
                navigateToTutorial = navController::navigateToTutorial
            )
        }
        composable(
            route = OnboardingDestination.Tutorial.route
        ) {
            TutorialRoute(
                navigateToMain = navController::navigateToMain
            )
        }
    }
}

sealed class OnboardingDestination(val route: String) {
    object Interest : OnboardingDestination("interest")
    object Tutorial : OnboardingDestination("tutorial")
}

fun NavController.navigateToTutorial() {
    navigate(OnboardingDestination.Tutorial.route)
}
