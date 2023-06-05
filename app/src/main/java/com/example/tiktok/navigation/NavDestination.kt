package com.example.tiktok.navigation

sealed class NavDestination(open val route: String) {
    object AuthGraph : NavDestination("authGraph") {
        object SignUp : NavDestination("signUp")
        object Username : NavDestination("username")
        object Email : NavDestination("email/{username}") {
            const val UsernameArgId = "username"
            fun createRoute(username: String) = "email/$username"
        }
        object Password : NavDestination("password")
        object Birthday : NavDestination("birthday")
        object Login : NavDestination("login")
        object LoginForm : NavDestination("loginForm")
    }

    object OnboardingGraph : NavDestination("onboardingGraph") {
        object Interest : NavDestination("interest")
        object Tutorial : NavDestination("tutorial")
    }
}
