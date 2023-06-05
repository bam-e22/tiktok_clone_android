package com.example.tiktok.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.authentication.onboarding.InterestRoute
import com.example.tiktok.ui.authentication.onboarding.TutorialRoute

fun NavGraphBuilder.addOnboardingGraph(
    navController: NavController,
) {
    navigation(
        route = NavDestination.OnboardingGraph.route,
        startDestination = NavDestination.OnboardingGraph.Interest.route
    ) {
        composable(
            route = NavDestination.OnboardingGraph.Interest.route
        ) {
            InterestRoute(
                navigateToTutorial = navController::navigateToTutorial
            )
        }
        composable(
            route = NavDestination.OnboardingGraph.Tutorial.route
        ) {
            TutorialRoute(
                navigateToMain = {}
            )
        }
    }
}

fun NavController.navigateToTutorial() {
    navigate(NavDestination.OnboardingGraph.Tutorial.route)
}
