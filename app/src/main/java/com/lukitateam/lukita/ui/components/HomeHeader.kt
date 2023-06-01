package com.lukitateam.lukita.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.theme.Primary

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    username: String,
    drawable: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(70.dp)
            .background(Primary)
            .padding(vertical = 4.dp, horizontal = 24.dp)
    ) {
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = "Hello, ",
                color = Color.White,
            )
            Text(
                text = username,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(60.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(R.drawable.logo_02),
                contentDescription = stringResource(R.string.app_name),
                modifier.size(60.dp),
            )
        }
    }
    Image(
        painter = painterResource(drawable),
        contentDescription = stringResource(R.string.wave_desc),
        modifier = modifier
            .fillMaxWidth()
    )
}

