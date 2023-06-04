package com.lukitateam.lukita.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun SplashScreen(
) {
    SplashScreenDesign()
}

@Composable
fun SplashScreenDesign(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
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

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun SplashScreenPreview() {
    LukitaTheme {
        SplashScreen()
    }
}