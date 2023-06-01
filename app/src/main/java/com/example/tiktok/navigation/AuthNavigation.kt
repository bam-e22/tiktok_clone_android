package com.example.tiktok.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tiktok.ui.authentication.login.LoginRoute
import com.example.tiktok.ui.authentication.signup.SignUpRoute

const val AuthGraphRoute = "auth_graph"
const val SignUpNavRoute = "signup"
const val LoginNavRoute = "login"

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
