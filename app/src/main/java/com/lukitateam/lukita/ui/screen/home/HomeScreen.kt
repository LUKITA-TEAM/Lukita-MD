package com.lukitateam.lukita.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.model.DummyPhotos
import com.lukitateam.lukita.model.Photo
import com.lukitateam.lukita.model.styleArts
import com.lukitateam.lukita.ui.components.HomeHeader
import com.lukitateam.lukita.ui.components.ListRecommendation
import com.lukitateam.lukita.ui.components.PhotoItem
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column {
        HomeHeader(
            username = "John",
            drawable = R.drawable.wave_hs_2,
        )
        Dashboard()
    }
}

@Composable
fun Dashboard(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Dashboard",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier.padding(horizontal = 24.dp)
    )
    LazyRow(
        modifier = modifier
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(styleArts, key = { it.styleArt }) {style ->
            ListRecommendation(style)
        }
    }
    PhotoGrid()
}

@Composable
fun PhotoGrid(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(DummyPhotos.photos, key = { it.id }) {photo ->
            PhotoItem(
                photo.id,
                photo.name,
                photo.styleArt,
                photo.photoUrl,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPrev() {
    LukitaTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoGridPreview() {
    LukitaTheme() {
        PhotoItem(
            id = 1,
            name = "test images",
            styleArt = "Naturalism",
            photoUrl = "https://raw.githubusercontent.com/dicodingacademy/assets/main/android_compose_academy/pahlawan/5.jpg"
        )
    }
}