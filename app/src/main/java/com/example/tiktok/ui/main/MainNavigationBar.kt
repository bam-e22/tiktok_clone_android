package com.example.tiktok.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tiktok.navigation.MainScreen

@Composable
fun MainNavigationBar(navController: NavHostController) {
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
