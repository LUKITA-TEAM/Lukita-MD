package com.lukitateam.lukita.ui.components.navigationBar

import androidx.annotation.DrawableRes
import com.lukitateam.lukita.R

sealed class NavScreen(
    val id : Int,
    val title : String,
    @DrawableRes val selectedIconId : Int,
    @DrawableRes val unselectedIconId : Int,
) {
    object Home: NavScreen(id = 0, title = "Home", selectedIconId = R.drawable.baseline_home, unselectedIconId = R.drawable.baseline_home)
    object Profile: NavScreen(id = 1, title = "Profile", selectedIconId = R.drawable.baseline_settings, unselectedIconId = R.drawable.baseline_settings)
    object MyImg: NavScreen(id = 2, title = "My Image", selectedIconId = R.drawable.baseline_photo_library, unselectedIconId = R.drawable.baseline_photo_library)
}