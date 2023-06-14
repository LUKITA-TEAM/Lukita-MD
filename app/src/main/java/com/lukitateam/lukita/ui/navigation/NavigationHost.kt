package com.lukitateam.lukita.ui.navigation

import CameraScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lukitateam.lukita.ui.screen.home.HomeScreen
import com.lukitateam.lukita.ui.screen.login.LoginScreen
import com.lukitateam.lukita.ui.screen.profile.ProfileScreen
import com.lukitateam.lukita.ui.screen.register.RegisterScreen
import com.lukitateam.lukita.ui.screen.splash.SplashScreen
import com.lukitateam.lukita.ui.screen.welcome.WelcomeScreen1
import com.lukitateam.lukita.ui.screen.welcome.WelcomeScreen2
import com.lukitateam.lukita.ui.screen.welcome.WelcomeScreen3

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.padding(innerPadding)
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navController
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                navController
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                navController
            )
        }
        composable(Screen.Welcome1.route) {
            WelcomeScreen1()
        }
        composable(Screen.Welcome2.route) {
            WelcomeScreen2()
        }
        composable(Screen.Welcome3.route) {
            WelcomeScreen3()
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Camera.route) {
            CameraScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}