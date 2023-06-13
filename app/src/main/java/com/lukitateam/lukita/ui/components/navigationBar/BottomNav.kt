package com.lukitateam.lukita.ui.components.navigationBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun BottomNav() {
    var currentSelectedNavScreenId by remember { mutableStateOf(0) }
    BottomAppBar(
        contentPadding = PaddingValues(horizontal = 50.dp),
        tonalElevation = 2.dp,
    ) {
        NavScreen.Home.let { home ->
            NavItem(
                item = home,
                isSelected = home.id == currentSelectedNavScreenId,
            ) {
                currentSelectedNavScreenId = home.id
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        NavScreen.MyImg.let { myImg ->
            NavItem(
                item = myImg,
                isSelected = myImg.id == currentSelectedNavScreenId,
            ) {
                currentSelectedNavScreenId = myImg.id
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        NavScreen.Profile.let { profile ->
            NavItem(
                item = profile,
                isSelected = profile.id == currentSelectedNavScreenId
            ) {
                currentSelectedNavScreenId = profile.id
            }
        }
    }
}
