package com.example.tiktok.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.tiktok.ui.authentication.login.LoginFormRoute
import com.example.tiktok.ui.authentication.login.LoginRoute
import com.example.tiktok.ui.authentication.signup.BirthdayRoute
import com.example.tiktok.ui.authentication.signup.EmailRoute
import com.example.tiktok.ui.authentication.signup.PasswordRoute
import com.example.tiktok.ui.authentication.signup.SignUpRoute
import com.example.tiktok.ui.authentication.signup.UserNameRoute
import com.example.tiktok.ui.authentication.viewmodel.LoginFormViewModel
import com.example.tiktok.ui.authentication.viewmodel.SignUpFormViewModel
import com.example.tiktok.ui.home.MainScreen

fun NavGraphBuilder.addAuthNavGraph(
    navController: NavController,
) {
    navigation(
        route = NavDestination.AuthGraph.route,
        startDestination = NavDestination.AuthGraph.SignUp.route
    ) {
        composable(
            route = NavDestination.AuthGraph.SignUp.route
        ) {
            SignUpRoute(
                navigateToLogin = navController::navigateToLogin,
                navigateToUserName = navController::navigateToUserName,
            )
        }
        composable(
            route = NavDestination.AuthGraph.Username.route
        ) {
            val viewModel = hiltViewModel<SignUpFormViewModel>()
            UserNameRoute(
                navigateBack = navController::popBackStack,
                navigateToEmail = navController::navigateToEmail,
                viewModel = viewModel
            )
        }
        composable(
            route = NavDestination.AuthGraph.Email.route,
            arguments = listOf(navArgument(NavDestination.AuthGraph.Email.UsernameArgId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(NavDestination.AuthGraph.Email.UsernameArgId)
            checkNotNull(userName)
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(NavDestination.AuthGraph.Username.route)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            EmailRoute(
                navigateBack = navController::popBackStack,
                navigateToPassword = navController::navigateToPassword,
                userName = userName,
                viewModel = viewModel
            )
        }
        composable(
            route = NavDestination.AuthGraph.Password.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(NavDestination.AuthGraph.Username.route)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            PasswordRoute(
                navigateBack = navController::popBackStack,
                navigateToBirthday = navController::navigateToBirthday,
                viewModel = viewModel
            )
        }
        composable(
            route = NavDestination.AuthGraph.Birthday.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(NavDestination.AuthGraph.Username.route)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            BirthdayRoute(
                navigateBack = navController::popBackStack,
                navigateToOnboarding = navController::navigateToOnboarding,
                viewModel = viewModel
            )
        }
        composable(
            route = NavDestination.AuthGraph.Login.route
        ) {
            LoginRoute(
                navigateToSignUp = navController::popBackStack,
                navigateToLoginForm = navController::navigateToLoginForm
            )
        }
        composable(
            route = NavDestination.AuthGraph.LoginForm.route
        ) {
            val viewModel = hiltViewModel<LoginFormViewModel>()
            LoginFormRoute(
                navigateBack = navController::popBackStack,
                navigateToMain = {
                    navController.navigate("Main") // TODO: for test
                },
                viewModel = viewModel
            )
        }
        // TODO: for test
        composable(
            route = "Main"
        ) {
            MainScreen()
        }
    }
}

fun NavController.navigateToLogin() {
    navigate(NavDestination.AuthGraph.Login.route)
}

fun NavController.navigateToUserName() {
    navigate(NavDestination.AuthGraph.Username.route)
}

fun NavController.navigateToEmail(username: String) {
    navigate(NavDestination.AuthGraph.Email.createRoute(username))
}

fun NavController.navigateToPassword() {
    navigate(NavDestination.AuthGraph.Password.route)
}

fun NavController.navigateToBirthday() {
    navigate(NavDestination.AuthGraph.Birthday.route)
}

fun NavController.navigateToOnboarding() {
    navigate(NavDestination.OnboardingGraph.route)
}

fun NavController.navigateToLoginForm() {
    navigate(NavDestination.AuthGraph.LoginForm.route)
}
