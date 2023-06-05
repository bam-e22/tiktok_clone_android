package com.example.tiktok.navigation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        MainNavGraph(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        val list = listOf(
            MainScreen.VideoTimeline,
            MainScreen.Discover,
            MainScreen.Camera,
            MainScreen.Inbox,
            MainScreen.Profile
        )
        list.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if (currentRoute == screen.route) {
                        Icon(imageVector = list[index].selectedIcon, contentDescription = null)
                    } else {
                        Icon(imageVector = list[index].unselectedIcon, contentDescription = null)
                    }
                }
            )
        }
    }
}
