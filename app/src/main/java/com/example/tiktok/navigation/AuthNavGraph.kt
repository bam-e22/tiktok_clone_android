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

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
) {
    navigation(
        route = TikTokNavGraph.Auth.route,
        startDestination = AuthScreen.SignUp.route
    ) {
        composable(
            route = AuthScreen.SignUp.route
        ) {
            SignUpRoute(
                navigateToLogin = navController::navigateToLogin,
                navigateToUserName = navController::navigateToUserName,
            )
        }
        composable(
            route = AuthScreen.Username.route
        ) {
            val viewModel = hiltViewModel<SignUpFormViewModel>()
            UserNameRoute(
                navigateBack = navController::popBackStack,
                navigateToEmail = navController::navigateToEmail,
                viewModel = viewModel
            )
        }
        composable(
            route = AuthScreen.Email.route,
            arguments = listOf(navArgument(AuthScreen.Email.UsernameArgId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(AuthScreen.Email.UsernameArgId)
            checkNotNull(userName)
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AuthScreen.Username.route)
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
            route = AuthScreen.Password.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AuthScreen.Username.route)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            PasswordRoute(
                navigateBack = navController::popBackStack,
                navigateToBirthday = navController::navigateToBirthday,
                viewModel = viewModel
            )
        }
        composable(
            route = AuthScreen.Birthday.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(AuthScreen.Username.route)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            BirthdayRoute(
                navigateBack = navController::popBackStack,
                navigateToOnboarding = navController::navigateToOnboarding,
                viewModel = viewModel
            )
        }
        composable(
            route = AuthScreen.Login.route
        ) {
            LoginRoute(
                navigateToSignUp = navController::popBackStack,
                navigateToLoginForm = navController::navigateToLoginForm
            )
        }
        composable(
            route = AuthScreen.LoginForm.route
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

sealed class AuthScreen(val route: String) {
    object SignUp : AuthScreen("signUp")
    object Username : AuthScreen("username")
    object Email : AuthScreen("email/{username}") {
        const val UsernameArgId = "username"
        fun createRoute(username: String) = "email/$username"
    }

    object Password : AuthScreen("password")
    object Birthday : AuthScreen("birthday")
    object Login : AuthScreen("login")
    object LoginForm : AuthScreen("loginForm")
}

fun NavController.navigateToLogin() {
    navigate(AuthScreen.Login.route)
}

fun NavController.navigateToUserName() {
    navigate(AuthScreen.Username.route)
}

fun NavController.navigateToEmail(username: String) {
    navigate(AuthScreen.Email.createRoute(username))
}

fun NavController.navigateToPassword() {
    navigate(AuthScreen.Password.route)
}

fun NavController.navigateToBirthday() {
    navigate(AuthScreen.Birthday.route)
}

fun NavController.navigateToOnboarding() {
    navigate(TikTokNavGraph.Onboarding.route)
}

fun NavController.navigateToLoginForm() {
    navigate(AuthScreen.LoginForm.route)
}

fun NavController.navigateToMain() {
    navigate(TikTokNavGraph.Main.route)
}
