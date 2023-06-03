@file:OptIn(ExperimentalFoundationApi::class)

package com.lukitateam.lukita.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.model.DummyPicture
import com.lukitateam.lukita.model.styleArts
import com.lukitateam.lukita.ui.components.HomeHeader
import com.lukitateam.lukita.ui.components.ListRecommendation
import com.lukitateam.lukita.ui.components.PhotoItem
import com.lukitateam.lukita.ui.theme.LukitaTheme
import kotlin.random.Random

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column {
        HomeHeader(
            username = "John",
            drawable = R.drawable.wave_homescreen,
        )
        Dashboard()
    }
}

@Composable
fun Dashboard(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Dashboard",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier.padding(horizontal = 16.dp)
    )
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(styleArts, key = { it.styleArt }) { style ->
            ListRecommendation(style)
        }
    }
//    PhotoGrid()
    ColorGridExample()
}

/*Bug! Foto tidak mau tampil*/
//@Composable
//fun PhotoGrid(
//    modifier: Modifier = Modifier
//) {
//    Box(modifier = modifier) {
//        LazyVerticalStaggeredGrid(
//            columns = StaggeredGridCells.Fixed(2),
//            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(DummyPicture.photos) {photo ->
//                val height = Random.nextInt(100, 300).dp
//                PhotoItem(
//                    height = height,
//                    name = photo.name,
//                    photoUrl = photo.photoUrl
//                )
//            }
//        }
//    }
//}


val items = (1..100).map {
    ListItem(
        height = Random.nextInt(100, 300).dp,
        color = Color(
            Random.nextLong(0xFFFFFFFF)
        ).copy(alpha = 1f)
    )
}
@Composable
fun ColorGridExample() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(128.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            RandomColorBox(item = item)
        }
    }
}

data class ListItem(
    val height: Dp,
    val color: Color,
)

@Composable
fun RandomColorBox(item: ListItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(item.height)
            .clip(RoundedCornerShape(10.dp))
            .background(item.color)
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPrev() {
    LukitaTheme {
        HomeScreen()
    }
}
