package com.example.tiktok.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Textsms
import androidx.compose.material.icons.outlined.Whatshot
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.discover.DiscoverRoute
import com.example.tiktok.ui.inbox.InboxRoute
import com.example.tiktok.ui.setting.SettingRoute
import com.example.tiktok.ui.user.ProfileRoute
import com.example.tiktok.ui.video.CameraRoute
import com.example.tiktok.ui.video.TimelineRoute

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
) {
    navigation(
        route = TikTokNavGraph.Main.route,
        startDestination = MainScreen.BottomNavigation.VideoTimeline.route
    ) {
        composable(
            route = MainScreen.BottomNavigation.VideoTimeline.route
        ) {
            TimelineRoute(
                navigateToSignUp = navController::navigateToSignUp,
            )
        }
        composable(
            route = MainScreen.BottomNavigation.Discover.route
        ) {
            DiscoverRoute()
        }
        composable(
            route = MainScreen.BottomNavigation.Camera.route
        ) {
            CameraRoute()
        }
        composable(
            route = MainScreen.BottomNavigation.Inbox.route
        ) {
            InboxRoute()
        }
        composable(
            route = MainScreen.BottomNavigation.Profile.route
        ) {
            ProfileRoute(
                navigateToSettings = navController::navigateToSettings
            )
        }
        composable(
            route = MainScreen.Settings.route
        ) {
            SettingRoute(
                navigateBack = navController::popBackStack
            )
        }
    }
}

sealed class MainScreen(val route: String) {

    object Settings : MainScreen("settings")

    sealed class BottomNavigation(
        route: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val text: String,
    ) : MainScreen(route) {
        object VideoTimeline : BottomNavigation(
            "timeline",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            text = "Home"
        )

        object Discover : BottomNavigation(
            "discover",
            selectedIcon = Icons.Filled.Whatshot,
            unselectedIcon = Icons.Outlined.Whatshot,
            text = "Discover"
        )

        object Camera : BottomNavigation(
            "camera",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            text = ""
        )

        object Inbox : BottomNavigation(
            "inbox",
            selectedIcon = Icons.Filled.Textsms,
            unselectedIcon = Icons.Outlined.Textsms,
            text = "Inbox"
        )

        object Profile : BottomNavigation(
            "profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            text = "Profile"
        )
    }
}

fun NavController.navigateToSignUp() {
    navigate(TikTokNavGraph.Auth.route)
}

fun NavController.navigateToSettings() {
    navigate(MainScreen.Settings.route)
}
