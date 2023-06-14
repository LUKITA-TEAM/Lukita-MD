package com.lukitateam.lukita.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lukitateam.lukita.R
import com.lukitateam.lukita.data.response.GalleryResponse
import com.lukitateam.lukita.ui.common.UiState
import com.lukitateam.lukita.ui.components.HomeHeader
import com.lukitateam.lukita.ui.components.PhotoItem
import com.lukitateam.lukita.ui.theme.LukitaTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.galleryState.collectAsState(initial = null)

    var galleryList by remember { mutableStateOf<UiState<List<GalleryResponse>>>(UiState.Loading) }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
            }
        }
    }

    scope.launch {
        galleryList = viewModel.getGallery()
    }

    Column {
        HomeHeader(
            username = "John",
            drawable = R.drawable.wave_homescreen,
        )

        when (val response = galleryList) {
            is UiState.Success -> {
                Dashboard(response.data)
            }

            is UiState.Error -> {
                val errorMessage = response.errorMessage
                Log.e("Home Screen", errorMessage)
            }

            UiState.Loading -> {
                // Show a loading indicator
            }
        }
    }
}

@Composable
fun Dashboard(
    data: List<GalleryResponse>,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Dashboard",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier.padding(horizontal = 16.dp)
    )
    // Next Feature
    /*LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(styleArts, key = { it.styleArt }) { style ->
            ListRecommendation(style)
        }
    } */
    PhotoGrid(data)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGrid(
    data: List<GalleryResponse>, modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(data) { img ->
                PhotoItem(
                    type = img.type, photoUrl = img.url
                )
            }
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
