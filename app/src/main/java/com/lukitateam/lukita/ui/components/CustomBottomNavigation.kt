package com.lukitateam.lukita.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun CustomBottomNavigation() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(80.dp)
//            .paint(
//                painter = painterResource(),
//                contentScale = ContentScale.FillHeight
//            )
            .padding(horizontal = 40.dp)
    ) {
        listOf(Icons.Filled.Home, Icons.Filled.Person).map { image ->
            IconButton(onClick = {}) {
                Icon(
                    imageVector = image,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }
    }
}

@Composable
fun FabGroup(
    animationProgress: Float = 0f,
    renderEffect: RenderEffect? = null,
    toggleAnimation: () -> Unit = {},
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 4.dp)
            .graphicsLayer { this.renderEffect = renderEffect },
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedFab(
            icon = Icons.Default.Add,
            modifier = Modifier
                .rotate(
                    225 * FastOutSlowInEasing.transform(0f)
                ),
            onClick = toggleAnimation,
            backgroundColor = Color.Transparent
        )
    }
}

@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: ImageVector? = null,
    opacity: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = {},
        elevation = FloatingActionButtonDefaults
            .elevation(0.dp, 0.dp, 0.dp, 0.dp),
        containerColor = backgroundColor,
        modifier = modifier.scale(1.25f)
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = opacity)
            )
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun NavPreview() {
    LukitaTheme {
        CustomBottomNavigation()
        FabGroup()
    }
}