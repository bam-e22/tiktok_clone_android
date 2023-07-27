package com.example.tiktok.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tiktok.navigation.MainDestination
import com.example.tiktok.ui.utils.Sizes.MediumIconSize

@Composable
fun MainNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    fun isTimelineRoute() = currentRoute == MainDestination.BottomNavigation.VideoTimeline.route

    NavigationBar(
        containerColor = if (isTimelineRoute()) {
            Color.Black
        } else {
            Color.White
        },
        tonalElevation = 0.dp
    ) {
        val list = listOf(
            MainDestination.BottomNavigation.VideoTimeline,
            MainDestination.BottomNavigation.Discover,
            MainDestination.BottomNavigation.Camera,
            MainDestination.BottomNavigation.Inbox,
            MainDestination.BottomNavigation.Profile
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
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = if (isTimelineRoute()) Color.White else Color.Black,
                    selectedTextColor = if (isTimelineRoute()) Color.White else Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = if (isTimelineRoute()) Color.Black else Color.White
                ),
                interactionSource = MutableInteractionSource(),
                alwaysShowLabel = true,
                label = {
                    if (list[index].text.isNotEmpty()) {
                        Text(
                            list[index].text,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                },
                icon = {
                    if (screen.route == MainDestination.BottomNavigation.Camera.route) {
                        TiktokAddButton(
                            backgroundColor = if (isTimelineRoute()) Color.White else Color.Black,
                            iconTintColor = if (isTimelineRoute()) Color.Black else Color.White,
                            icon = list[index].selectedIcon
                        )
                    } else {
                        if (currentRoute == screen.route) {
                            Icon(
                                modifier = Modifier.size(MediumIconSize),
                                imageVector = list[index].selectedIcon,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                modifier = Modifier.size(MediumIconSize),
                                imageVector = list[index].unselectedIcon,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    }
}
