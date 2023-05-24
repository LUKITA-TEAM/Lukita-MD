package com.primagiant.lukita

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.primagiant.lukita.ui.navigation.Screen
import com.primagiant.lukita.ui.screen.home.HomeScreen
import com.primagiant.lukita.ui.screen.login.LoginScreen
import com.primagiant.lukita.ui.screen.register.RegisterScreen
import com.primagiant.lukita.ui.screen.splash.SplashScreen
import com.primagiant.lukita.ui.screen.welcome.WelcomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LukitaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // jika route tidak menggunakan bottom bar matikan disini
            /* if (currentRoute != Screen.DetailItem.route) {
                BottomBar(navController)
            } */
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen()
            }
            composable(Screen.Login.route) {
                LoginScreen()
            }
            composable(Screen.Register.route) {
                RegisterScreen()
            }
            composable(Screen.Welcome.route) {
                WelcomeScreen()
            }
            composable(Screen.Home.route) {
                HomeScreen()
            }
        }
    }
}
