package com.lukitateam.lukita.ui.navigation

sealed class Screen (val route: String) {
    object Splash : Screen("splash")
    object Welcome1 : Screen("welcome_1")
    object Welcome2 : Screen("welcome_2")
    object Welcome3 : Screen("welcome_3")
    object Login : Screen("login")
    object Register: Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Camera : Screen("camera")
    object Detail : Screen("detail")
}