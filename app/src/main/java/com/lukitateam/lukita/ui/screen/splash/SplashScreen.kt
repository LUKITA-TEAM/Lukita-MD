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
import androidx.navigation.NavController
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    var animationStart by remember {
        mutableStateOf(false)
    }
    val animation = animateFloatAsState(
        targetValue = if (animationStart) 1f else 0f,
        animationSpec = tween(3000)
    )

    LaunchedEffect(key1 = true) {
        animationStart = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Login.route)
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