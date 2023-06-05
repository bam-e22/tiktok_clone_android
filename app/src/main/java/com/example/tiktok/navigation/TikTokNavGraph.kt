package com.example.tiktok.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TikTokNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TikTokNavGraph.Auth.route, // TODO: 메인 루트로 변경
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        authNavGraph(navController)
        addOnboardingGraph(navController)
    }
}

sealed class TikTokNavGraph(val route: String) {
    object Auth : TikTokNavGraph("authGraph")
    object Onboarding : TikTokNavGraph("onboardingGraph")
    object Main : TikTokNavGraph("mainGraph")
}
