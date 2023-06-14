package com.lukitateam.lukita.ui.components.navigationBar

import android.widget.Toast
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.navigation.Screen

enum class MultiFloatingState {
    Expanded,
    Collapsed,
}

@Composable
fun MultiFloatingButton(
    multiFloatingState: MultiFloatingState,
    navController: NavController,
    onMultiFabStateChange: (MultiFloatingState) -> Unit,
    items: List<MinFabItem>
) {
    val transition = updateTransition(
        targetState = multiFloatingState,
        label = "transition"
    )
    val rotate by transition.animateFloat(label = "rotate") {
        if (it == MultiFloatingState.Expanded) 315f else 0f
    }

    Column(
        horizontalAlignment = Alignment.End
    ) {
        if (transition.currentState == MultiFloatingState.Expanded) {
            items.forEach { item ->
                MinFab(
                    item = item,
                    text = item.label,
                    onMinFabItemClick = {
                        when(item.label) {
                            "Camera" -> {
                                navController.navigate(Screen.Camera.route)
                            }
                            "Gallery" -> {
                                // TODO (INTENT TO GALERY)
                            }
                            else -> {}
                        }

                    }
                )
                Spacer(modifier = Modifier.size(24.dp))
            }
        }
        FloatingActionButton(
            onClick = {
                onMultiFabStateChange(
                    if (transition.currentState == MultiFloatingState.Expanded) {
                        MultiFloatingState.Collapsed
                    } else {
                        MultiFloatingState.Expanded
                    }
                )
            },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp),
            modifier = Modifier.rotate(rotate)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_add),
                contentDescription = "add",
                modifier = Modifier
                    .size(24.dp)
                    .rotate(rotate)
            )
        }

    }
}

@Composable
fun MinFab(
    item: MinFabItem,
    text: String,
    onMinFabItemClick: (MinFabItem) -> Unit,
) {
    val buttonColor = MaterialTheme.colorScheme.primaryContainer
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 16.dp)
        )
        Canvas(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    onClick = {
                        onMinFabItemClick.invoke(item)
                    },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 20.dp,
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                ),
        ) {
            drawCircle(
                color = buttonColor,
                radius = 60f,
            )
            drawImage(
                image = item.icon,
                topLeft = Offset(
                    center.x - (item.icon.width / 2),
                    center.y - (item.icon.width / 2),
                )
            )
        }
    }
}
