package com.lukitateam.lukita.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.theme.Primary

@Composable
fun AuthHeader(
    text: String,
    drawable: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(370.dp)
            .background(Primary)
            .padding(60.dp, 85.dp, 16.dp, 16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.logo_02),
                contentDescription = "Logo Lukita V2",
                modifier.size(61.dp)
            )
            Divider(
                thickness = 16.dp, color = Color.Transparent
            )
            Text(
                "Welcome",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                ),
            )
            Text(
                "to Lukita App",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                ),
            )
            Divider(
                thickness = 16.dp, color = Color.Transparent
            )
            Text(
                "Search for the art style you want!",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                ),
            )
            Divider(
                thickness = 24.dp, color = Color.Transparent
            )
            Text(
                text,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                ),
            )
        }
    }
    Image(
        painter = painterResource(drawable),
        contentDescription = null,
        modifier.fillMaxWidth()
    )
}