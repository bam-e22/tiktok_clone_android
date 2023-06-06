package com.example.tiktok.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.main.CameraScreen
import com.example.tiktok.ui.main.DiscoverScreen
import com.example.tiktok.ui.main.InboxScreen
import com.example.tiktok.ui.main.ProfileScreen
import com.example.tiktok.ui.main.TimelineScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
) {
    navigation(
        route = TikTokNavGraph.Main.route,
        startDestination = MainScreen.VideoTimeline.route
    ) {
        composable(
            route = MainScreen.VideoTimeline.route
        ) {
            TimelineScreen(
                navigateToSignUp = navController::navigateToSignUp
            )
        }
        composable(
            route = MainScreen.Discover.route
        ) {
            DiscoverScreen()
        }
        composable(
            route = MainScreen.Camera.route
        ) {
            CameraScreen()
        }
        composable(
            route = MainScreen.Inbox.route
        ) {
            InboxScreen()
        }
        composable(
            route = MainScreen.Profile.route
        ) {
            ProfileScreen()
        }
    }
}

sealed class MainScreen(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val text: String,
) {
    object VideoTimeline : MainScreen(
        route = "timeline",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        text = "Home"
    )

    object Discover : MainScreen(
        route = "discover",
        selectedIcon = Icons.Filled.Build,
        unselectedIcon = Icons.Outlined.Build,
        text = "Discover"
    )

    object Camera : MainScreen(
        route = "camera",
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Outlined.Add,
        text = "Discover"
    )

    object Inbox : MainScreen(
        route = "inbox",
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email,
        text = "Discover"
    )

    object Profile : MainScreen(
        route = "profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        text = "Discover"
    )
}

fun NavController.navigateToSignUp() {
    navigate(TikTokNavGraph.Auth.route)
}
