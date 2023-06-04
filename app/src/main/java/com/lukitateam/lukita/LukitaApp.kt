package com.lukitateam.lukita

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
import com.lukitateam.lukita.ui.navigation.Screen
import com.lukitateam.lukita.ui.screen.home.HomeScreen
import com.lukitateam.lukita.ui.screen.login.LoginScreen
import com.lukitateam.lukita.ui.screen.register.RegisterScreen
import com.lukitateam.lukita.ui.screen.splash.SplashScreen
import com.lukitateam.lukita.ui.screen.welcome.WelcomeScreen1
import com.lukitateam.lukita.ui.screen.welcome.WelcomeScreen2
import com.lukitateam.lukita.ui.screen.welcome.WelcomeScreen3

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
                SplashScreen(
                    navController
                )
            }
            composable(Screen.Login.route) {
                LoginScreen()
            }
            composable(Screen.Register.route) {
                RegisterScreen()
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
        }
    }
}
