package com.example.tiktok.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
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

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
) {
    navigation(
        route = TopLevelDestination.Auth.route,
        startDestination = AuthDestination.SignUp.route
    ) {
        composable(
            route = AuthDestination.SignUp.route
        ) {
            val viewModel = hiltViewModel<SignUpFormViewModel>()
            SignUpRoute(
                navigateToLogin = navController::navigateToLogin,
                navigateToUserName = navController::navigateToUserName,
                navigateToMain = navController::navigateToMain,
                viewModel = viewModel
            )
        }
        composable(
            route = AuthDestination.Username.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AuthDestination.SignUp.route)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            UserNameRoute(
                navigateBack = navController::popBackStack,
                navigateToEmail = navController::navigateToEmail,
                viewModel = viewModel
            )
        }
        composable(
            route = AuthDestination.Email.route,
            arguments = listOf(navArgument(AuthDestination.Email.UsernameArgId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(AuthDestination.Email.UsernameArgId)
            checkNotNull(userName)
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AuthDestination.SignUp.route)
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
            route = AuthDestination.Password.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AuthDestination.SignUp.route)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            PasswordRoute(
                navigateBack = navController::popBackStack,
                navigateToBirthday = navController::navigateToBirthday,
                viewModel = viewModel
            )
        }
        composable(
            route = AuthDestination.Birthday.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AuthDestination.SignUp.route)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            BirthdayRoute(
                navigateBack = navController::popBackStack,
                navigateToOnboarding = navController::navigateToOnboarding,
                viewModel = viewModel
            )
        }
        composable(
            route = AuthDestination.Login.route
        ) {
            LoginRoute(
                navigateToSignUp = navController::popBackStack,
                navigateToLoginForm = navController::navigateToLoginForm
            )
        }
        composable(
            route = AuthDestination.LoginForm.route
        ) {
            val viewModel = hiltViewModel<LoginFormViewModel>()
            LoginFormRoute(
                navigateBack = navController::popBackStack,
                navigateToMain = navController::navigateToMain,
                viewModel = viewModel
            )
        }
    }
}

sealed class AuthDestination(val route: String) {
    object SignUp : AuthDestination("signUp")
    object Username : AuthDestination("username")
    object Email : AuthDestination("email/{username}") {
        const val UsernameArgId = "username"
        fun createRoute(username: String) = "email/$username"
    }

    object Password : AuthDestination("password")
    object Birthday : AuthDestination("birthday")
    object Login : AuthDestination("login")
    object LoginForm : AuthDestination("loginForm")
}

fun NavController.navigateToLogin() {
    navigate(AuthDestination.Login.route)
}

fun NavController.navigateToUserName() {
    navigate(AuthDestination.Username.route)
}

fun NavController.navigateToEmail(username: String) {
    navigate(AuthDestination.Email.createRoute(username))
}

fun NavController.navigateToPassword() {
    navigate(AuthDestination.Password.route)
}

fun NavController.navigateToBirthday() {
    navigate(AuthDestination.Birthday.route)
}

fun NavController.navigateToOnboarding() {
    navigate(TopLevelDestination.Onboarding.route)
}

fun NavController.navigateToLoginForm() {
    navigate(AuthDestination.LoginForm.route)
}

fun NavController.navigateToMain() {
    navigate(TopLevelDestination.Main.route) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
        }
    }
}
