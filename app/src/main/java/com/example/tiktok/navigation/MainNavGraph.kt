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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.discover.DiscoverRoute
import com.example.tiktok.ui.inbox.InboxRoute
import com.example.tiktok.ui.timeline.TimelineRoute
import com.example.tiktok.ui.timeline.viewmodel.TimelineViewModel
import com.example.tiktok.ui.user.ProfileRoute
import com.example.tiktok.ui.video.CameraRoute

fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
) {
    navigation(
        route = TopLevelDestination.Main.route,
        startDestination = MainDestination.BottomNavigation.VideoTimeline.route
    ) {
        composable(
            route = MainDestination.BottomNavigation.VideoTimeline.route
        ) {
            val viewModel = hiltViewModel<TimelineViewModel>()
            TimelineRoute(
                viewModel = viewModel,
            )
        }
        composable(
            route = MainDestination.BottomNavigation.Discover.route
        ) {
            DiscoverRoute()
        }
        composable(
            route = MainDestination.BottomNavigation.Camera.route
        ) {
            CameraRoute()
        }
        composable(
            route = MainDestination.BottomNavigation.Inbox.route
        ) {
            InboxRoute()
        }
        composable(
            route = MainDestination.BottomNavigation.Profile.route
        ) {
            ProfileRoute(
                navigateToSettings = navController::navigateToSetting
            )
        }

        settingNavGraph(navController)
    }
}

sealed class MainDestination(val route: String) {

    object Setting : MainDestination("setting")

    sealed class BottomNavigation(
        route: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val text: String,
    ) : MainDestination(route) {
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
    navigate(TopLevelDestination.Auth.route)
}

fun NavController.navigateToSetting() {
    navigate(MainDestination.Setting.route)
}

fun NavController.navigateToAuth() {
    navigate(TopLevelDestination.Auth.route)
}
