package com.lukitateam.lukita.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.components.DefaultHeader
import com.lukitateam.lukita.ui.theme.LukitaTheme
import com.lukitateam.lukita.ui.theme.Primary

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultHeader(
            stringResource(R.string.detail_header),
            Color.Black
        )
        Content(modifier = modifier.padding(top = 8.dp))
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .width(360.dp)
                .height(380.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(R.drawable.example_image1),
                contentDescription = stringResource(R.string.style_art_naturalism),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(20.dp)),
            )
            Box(
                modifier = modifier
                    .fillMaxHeight(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = stringResource(R.string.style_art_naturalism),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .width(275.dp)
                        .height(55.dp)
                        .background(Primary)
                        .clip(RoundedCornerShape(20.dp)),

                    )
            }
        }
        Text(
            text = stringResource(R.string.detail_desc),
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
                .padding(vertical = 32.dp, horizontal = 8.dp),
        )
        RelatedImage()
    }
}

@Composable
fun RelatedImage(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.related_img_header),
            textAlign = TextAlign.Center,
            modifier = modifier.padding(vertical = 8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    LukitaTheme {
        DetailScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    LukitaTheme {
        Content()
    }
}