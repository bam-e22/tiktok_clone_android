package com.example.tiktok.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.setting.SettingRoute
import com.example.tiktok.ui.setting.viewmodel.SettingViewModel

fun NavGraphBuilder.etcNavGraph(
    navController: NavController
) {
    navigation(
        route = MainDestination.Etc.route,
        startDestination = EtcDestination.Settings.route
    ) {
        composable(
            route = EtcDestination.Settings.route
        ) {
            val viewModel = hiltViewModel<SettingViewModel>()
            SettingRoute(
                navigateBack = navController::popBackStack,
                navigateToAuth = navController::navigateToAuth,
                viewModel = viewModel
            )
        }
    }
}

sealed class EtcDestination(val route: String) {
    object Settings: EtcDestination("settings")
}
