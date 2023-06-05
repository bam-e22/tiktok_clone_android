package com.example.tiktok.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.authentication.onboarding.InterestRoute
import com.example.tiktok.ui.authentication.onboarding.TutorialRoute

fun NavGraphBuilder.onboardingGraph(
    navController: NavController,
) {
    navigation(
        route = TikTokNavGraph.Onboarding.route,
        startDestination = OnboardingScreen.Interest.route
    ) {
        composable(
            route = OnboardingScreen.Interest.route
        ) {
            InterestRoute(
                navigateToTutorial = navController::navigateToTutorial
            )
        }
        composable(
            route = OnboardingScreen.Tutorial.route
        ) {
            TutorialRoute(
                navigateToMain = navController::navigateToMain
            )
        }
    }
}

sealed class OnboardingScreen(val route: String) {
    object Interest : OnboardingScreen("interest")
    object Tutorial : OnboardingScreen("tutorial")
}

fun NavController.navigateToTutorial() {
    navigate(OnboardingScreen.Tutorial.route)
}
