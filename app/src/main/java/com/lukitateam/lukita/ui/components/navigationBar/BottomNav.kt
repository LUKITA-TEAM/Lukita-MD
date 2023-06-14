package com.lukitateam.lukita.ui.components.navigationBar

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lukitateam.lukita.ui.navigation.Screen
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun BottomNav(
    navController: NavController
) {
    val context = LocalContext.current
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
                navController.navigate(Screen.Home.route)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        NavScreen.MyImg.let { myImg ->
            NavItem(
                item = myImg,
                isSelected = myImg.id == currentSelectedNavScreenId,
            ) {
                currentSelectedNavScreenId = myImg.id
                Toast.makeText(context, "Feature dalam pengembangan", Toast.LENGTH_SHORT).show()
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        NavScreen.Profile.let { profile ->
            NavItem(
                item = profile,
                isSelected = profile.id == currentSelectedNavScreenId,
            ) {
                currentSelectedNavScreenId = profile.id
                navController.navigate(Screen.Profile.route)
            }
        }
    }
}
