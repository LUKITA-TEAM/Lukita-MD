package com.lukitateam.lukita.ui.components.navigationBar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun NavItem(
    item: NavScreen,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val iconId = if (isSelected) item.selectedIconId else item.unselectedIconId
    val iconAlpha = if (isSelected) 1f else 0.5f
    IconButton(onClick = { onClick }) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = item.title,
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = iconAlpha),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavItemPreview() {
    LukitaTheme() {
        NavItem(
            item = NavScreen.Home,
            isSelected = true
        ) {
            
        }
    }
}