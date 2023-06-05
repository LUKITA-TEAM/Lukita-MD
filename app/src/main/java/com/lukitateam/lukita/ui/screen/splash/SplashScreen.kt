package com.lukitateam.lukita.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    var animationStart by remember {
        mutableStateOf(false)
    }
    val animation = animateFloatAsState(
        targetValue = if (animationStart) 1f else 0f,
        animationSpec = tween(2500)
    )

    LaunchedEffect(key1 = true) {
        animationStart = true
        delay(3000)
        navController.popBackStack()
        viewModel.getSession().collect { user ->
            if (user != "") {
                navController.navigate(Screen.Home.route)
            } else {
                navController.navigate(Screen.Login.route)

            }
        }
    }
    SplashScreenDesign(animation.value)
}

@Composable
fun SplashScreenDesign(
    alpha: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .alpha(alpha)
    ) {
        Image(
            painter = painterResource(R.drawable.logo_01),
            contentDescription = "Logo Lukita",
            modifier
                .size(280.dp)
                .align(Alignment.Center)
        )
    }
}