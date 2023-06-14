package com.lukitateam.lukita

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lukitateam.lukita.ui.components.navigationBar.BottomNav
import com.lukitateam.lukita.ui.components.navigationBar.MinFabItem
import com.lukitateam.lukita.ui.components.navigationBar.MultiFloatingButton
import com.lukitateam.lukita.ui.components.navigationBar.MultiFloatingState
import com.lukitateam.lukita.ui.navigation.NavigationHost
import com.lukitateam.lukita.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LukitaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var multiFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }

    val items = listOf(
        MinFabItem(
            icon = ImageBitmap.imageResource(R.drawable.camera_add),
            label = "Camera",
            identifier = "CameraFab"
        ),
        MinFabItem(
            icon = ImageBitmap.imageResource(R.drawable.gallery_add),
            label = "Gallery",
            identifier = "GalleryFab"
        )
    )

    Scaffold(
//        floatingActionButton = {
//            when (currentRoute) {
//                (Screen.Splash.route) -> {}
//                else -> {
//                    MultiFloatingButton(
//                        multiFloatingState = multiFloatingState,
//                        onMultiFabStateChange = {
//                            multiFloatingState = it
//                        },
//                        items = items
//                    )
//                }
//            }
//        },
//        bottomBar = {
//            // jika route tidak menggunakan bottom bar matikan disini
//            when (currentRoute) {
//                (Screen.Splash.route) -> {}
//                else -> {
//                    BottomNav()
//                }
//            }
//        },
        modifier = modifier
    ) { innerPadding ->

        NavigationHost(
            navController = navController,
            startDestination = Screen.Camera.route,
            innerPadding = innerPadding,
        )

    }
}
