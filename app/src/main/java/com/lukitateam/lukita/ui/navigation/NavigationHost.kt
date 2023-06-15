package com.lukitateam.lukita.ui.navigation

import CameraScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lukitateam.lukita.data.response.ArtResponse
import com.lukitateam.lukita.ui.screen.detail.DetailScreen
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
        composable(Screen.Camera.route) {
            CameraScreen(
                navController = navController,
                onNavigate = {
                    navController.navigate(Screen.Detail.route)
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                goToDetail = {
                    navController.navigate(Screen.Detail.route)
                }
            )
        }
        composable(Screen.Detail.route) {
            val state =
                navController.previousBackStackEntry?.savedStateHandle?.get<ArtResponse>("artResponse")
            val filepath = navController.previousBackStackEntry?.savedStateHandle?.get<String>("file")
            if (state != null && filepath != null) {
                DetailScreen(
                    sharedState = state,
                    filepath = filepath,
                    navigateBack = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
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
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController, navigateBack = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}