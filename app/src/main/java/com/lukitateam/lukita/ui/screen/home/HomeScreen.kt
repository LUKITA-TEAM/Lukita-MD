package com.lukitateam.lukita.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.components.HomeHeader
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    HomeHeader(
        username = "John",
        drawable = R.drawable.wave_hs_2,
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPrev() {
    LukitaTheme {
        HomeScreen()
    }
}