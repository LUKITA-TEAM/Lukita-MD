package com.lukitateam.lukita.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun PhotoItem(
    height: Dp,
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = photoUrl,
        contentDescription = name,
        modifier = modifier
            .padding(8.dp)
            .size(60.dp)
    )
}

@Preview
@Composable
fun ImageItemPreview() {
    LukitaTheme {
        PhotoItem(height = 100.dp, name = "Naturalism", photoUrl = "")
    }
}