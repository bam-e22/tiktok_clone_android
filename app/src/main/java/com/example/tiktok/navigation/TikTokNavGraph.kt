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
import com.example.tiktok.ui.main.MainNavigationBar

@Composable
fun TikTokNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TikTokNavGraph.Auth.route, // TODO: 메인 루트로 변경
) {
    Scaffold(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val parentRoute = navBackStackEntry?.destination?.parent?.route

            if (parentRoute == TikTokNavGraph.Main.route) {
                MainNavigationBar(navController)
            }
        }
    )
    { innerPadding ->
        NavHost(
            modifier = modifier
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

sealed class TikTokNavGraph(val route: String) {
    object Auth : TikTokNavGraph("authGraph")
    object Onboarding : TikTokNavGraph("onboardingGraph")
    object Main : TikTokNavGraph("mainGraph")
}
