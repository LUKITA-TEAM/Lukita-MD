package com.primagiant.lukita.ui.navigation

sealed class Screen (val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register: Screen("register")
    object Home : Screen("home")
}