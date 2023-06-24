package com.example.tiktok.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tiktok.navigation.MainDestination
import com.example.tiktok.ui.theme.PrimaryPink
import com.example.tiktok.ui.utils.Sizes.MediumIconSize
import com.example.tiktok.ui.utils.Sizes.SmallIconSize

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
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .drawBehind {
                                    val width = 40.dp.toPx()
                                    val height = 35.dp.toPx()
                                    drawRoundRect(
                                        topLeft = Offset(
                                            size.width / 2f - (width / 2) - 6.dp.toPx(),
                                            size.height / 2f - (height / 2) - 14.dp.toPx()
                                        ),
                                        size = Size(
                                            width = width,
                                            height = height,
                                        ),
                                        cornerRadius = CornerRadius(6.dp.toPx()),
                                        color = Color(0xff61D4F0)
                                    )
                                    drawRoundRect(
                                        topLeft = Offset(
                                            size.width / 2f - (width / 2) + 6.dp.toPx(),
                                            size.height / 2f - (height / 2) - 14.dp.toPx()
                                        ),
                                        size = Size(
                                            width = width,
                                            height = height,
                                        ),
                                        cornerRadius = CornerRadius(6.dp.toPx()),
                                        color = PrimaryPink
                                    )
                                    drawRoundRect(
                                        topLeft = Offset(
                                            size.width / 2f - (width / 2),
                                            size.height / 2f - (height / 2) - 14.dp.toPx()
                                        ),
                                        size = Size(
                                            width = width,
                                            height = height,
                                        ),
                                        cornerRadius = CornerRadius(6.dp.toPx()),
                                        color = if (isTimelineRoute()) Color.White else Color.Black
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(SmallIconSize)
                                    .offset(y = (-14).dp),
                                imageVector = list[index].selectedIcon,
                                tint = if (isTimelineRoute()) Color.Black else Color.White,
                                contentDescription = null
                            )
                        }
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
