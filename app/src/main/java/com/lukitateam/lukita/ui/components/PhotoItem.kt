package com.lukitateam.lukita.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun PhotoItem(
    type: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = photoUrl,
        contentDescription = type,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    )
}

@Preview
@Composable
fun ImageItemPreview() {
    LukitaTheme {
        PhotoItem(type = "Naturalism", photoUrl = "https://storage.googleapis.com/dataset-lukita/Fauvism/683eb94a28911b3f46bf9e7095a42d56-2224102327.jpg")
    }
}