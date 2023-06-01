package com.example.tiktok.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.authentication.login.LoginRoute
import com.example.tiktok.ui.authentication.signup.SignUpRoute
import com.example.tiktok.ui.authentication.signup.UserNameRoute

const val AuthGraphRoute = "auth_graph"
const val SignUpNavRoute = "signup"
const val LoginNavRoute = "login"
const val UserNameNavRoute = "username"

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
                navigateToLogin = {
                    navController.navigate(LoginNavRoute)
                },
                navigateToUserName = {
                    navController.navigate(UserNameNavRoute)
                }
            )
        }
        composable(
            route = UserNameNavRoute
        ) {
            UserNameRoute(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = LoginNavRoute
        ) {
            LoginRoute(
                navigateToSignUp = {
                    navController.popBackStack()
                }
            )
        }
    }
}
