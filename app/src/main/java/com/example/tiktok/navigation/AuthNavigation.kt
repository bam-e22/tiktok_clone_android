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

const val AuthGraphRoute = "auth_graph"
const val SignUpNavRoute = "signup"
const val UserNameNavRoute = "username"
const val UserNameArgId = "username"
const val EmailNavRoute = "email/{$UserNameArgId}"
const val PasswordNavRoute = "password"
const val BirthdayNavRoute = "birthday"

const val LoginNavRoute = "login"
const val LoginFormNavRoute = "loginForm"

fun NavGraphBuilder.addAuthNavGraph(
    navController: NavController,
) {
    navigation(
        route = AuthGraphRoute,
        startDestination = SignUpNavRoute
    ) {
        composable(
            route = SignUpNavRoute
        ) {
            SignUpRoute(
                navigateToLogin = navController::navigateToLogin,
                navigateToUserName = navController::navigateToUserName,
            )
        }
        composable(
            route = UserNameNavRoute
        ) {
            val viewModel = hiltViewModel<SignUpFormViewModel>()
            UserNameRoute(
                navigateBack = navController::popBackStack,
                navigateToEmail = navController::navigateToEmail,
                viewModel = viewModel
            )
        }
        composable(
            route = EmailNavRoute,
            arguments = listOf(navArgument(UserNameArgId) { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(UserNameArgId)
            checkNotNull(userName)
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(UserNameNavRoute)
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
            route = PasswordNavRoute
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(UserNameNavRoute)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            PasswordRoute(
                navigateBack = navController::popBackStack,
                navigateToBirthday = navController::navigateToBirthday,
                viewModel = viewModel
            )
        }
        composable(
            route = BirthdayNavRoute
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(UserNameNavRoute)
            }
            val viewModel = hiltViewModel<SignUpFormViewModel>(parentEntry)
            BirthdayRoute(
                navigateBack = navController::popBackStack,
                navigateToOnboarding = navController::navigateToOnboarding,
                viewModel = viewModel
            )
        }
        composable(
            route = LoginNavRoute
        ) {
            LoginRoute(
                navigateToSignUp = navController::popBackStack,
                navigateToLoginForm = navController::navigateToLoginForm
            )
        }
        composable(
            route = LoginFormNavRoute
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
    navigate(LoginNavRoute)
}

fun NavController.navigateToUserName() {
    navigate(UserNameNavRoute)
}

fun NavController.navigateToEmail(userName: String) {
    navigate("email/$userName")
}

fun NavController.navigateToPassword() {
    navigate(PasswordNavRoute)
}

fun NavController.navigateToBirthday() {
    navigate(BirthdayNavRoute)
}

fun NavController.navigateToOnboarding() {
    navigate(OnboardingGraphRoute)
}

fun NavController.navigateToLoginForm() {
    navigate(LoginFormNavRoute)
}
