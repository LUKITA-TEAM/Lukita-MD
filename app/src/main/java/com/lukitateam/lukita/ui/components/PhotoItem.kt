package com.lukitateam.lukita.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun PhotoItem(
    id: Int,
    name: String,
    styleArt: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {}
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
        )
    }
}

@Preview
@Composable
fun ImageItemPreview() {
    LukitaTheme {
        PhotoItem(id = 1, name = "Naturalism", styleArt = "Naturalism", photoUrl = "")
    }
}