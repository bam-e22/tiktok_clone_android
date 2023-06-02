package com.example.tiktok.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun TikTokNavGraph(
    navController: NavHostController = rememberNavController(),
    //startDestination: String = AuthGraphRoute, // TODO: 메인 루트로 변경
    startDestination: String = OnboardingGraphRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        addAuthNavGraph(navController)
        addOnboardingGraph(navController)
    }
}
