package com.example.tiktok.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tiktok.ui.components.MainNavigationBar

@Composable
fun TikTokNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TikTokDestination.Auth.route,
) {
    Scaffold(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val parentRoute = navBackStackEntry?.destination?.parent?.route

            if (parentRoute == TikTokDestination.Main.route) {
                MainNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(
                    bottom = innerPadding.calculateBottomPadding()
                ),
            navController = navController,
            startDestination = startDestination
        ) {
            authNavGraph(navController)
            onboardingNavGraph(navController)
            mainNavGraph(navController)
        }
    }
}

sealed class TikTokDestination(val route: String) {
    object Auth : TikTokDestination("authGraph")
    object Onboarding : TikTokDestination("onboardingGraph")
    object Main : TikTokDestination("mainGraph")
}
