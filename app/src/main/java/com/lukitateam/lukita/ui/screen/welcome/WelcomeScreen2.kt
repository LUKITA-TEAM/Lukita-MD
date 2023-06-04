package com.lukitateam.lukita.ui.screen.welcome

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun WelcomeScreen2(
    modifier: Modifier = Modifier,
) {
    LukitaTheme {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(R.drawable.wave_ws_2_above),
                    contentDescription = stringResource(id = R.string.wave_desc),
                    alignment = Alignment.TopEnd
                )
            }
            MainContentWS2()
            Box(
                modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(R.drawable.wave_ws_2_bottom),
                    contentDescription = stringResource(R.string.wave_desc)
                )
            }
        }
    }
}

@Composable
fun MainContentWS2(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ws_2),
            contentDescription = stringResource(R.string.welcome_img),
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = stringResource(R.string.welcome_header),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = stringResource(R.string.welcome_desc),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun WS2Preview() {
    LukitaTheme {
        WelcomeScreen2()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContentWS2() {
    LukitaTheme {
        MainContentWS2()
    }
}