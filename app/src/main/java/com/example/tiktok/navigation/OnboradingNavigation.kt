package com.example.tiktok.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.authentication.onboarding.InterestRoute

const val OnboardingGraphRoute = "onboarding"
const val InterestNavRoute = "interest"

fun NavGraphBuilder.addOnboardingGraph(
    navController: NavController
) {
    navigation(
        route = OnboardingGraphRoute,
        startDestination = InterestNavRoute
    ) {
        composable(
            route = InterestNavRoute
        ) {
            InterestRoute(
                navigateToTutorial = {}
            )
        }
    }
}
