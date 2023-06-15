package com.lukitateam.lukita.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.lukitateam.lukita.R
import com.lukitateam.lukita.data.response.ArtResponse
import com.lukitateam.lukita.data.response.GalleryResponse
import com.lukitateam.lukita.ui.common.UiState
import com.lukitateam.lukita.ui.components.HomeHeader
import com.lukitateam.lukita.util.downloadImageToFile
import com.lukitateam.lukita.util.reduceFileImage
import com.lukitateam.lukita.util.rotateFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    goToDetail: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.galleryState.collectAsState(initial = null)

    var galleryList by remember { mutableStateOf<UiState<List<GalleryResponse>>>(UiState.Loading) }
    var prediction by remember { mutableStateOf<UiState<ArtResponse>>(UiState.Loading) }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Log.i("HomeScreen", success.toString())
            }
        }
    }

    scope.launch {
        galleryList = viewModel.getGallery()
    }

    Column {
        HomeHeader(
            username = "Coco",
            drawable = R.drawable.wave_homescreen,
        )

        when (val response = galleryList) {
            is UiState.Success -> {
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
                PhotoGrid(data = response.data, onItemClicked = { url ->
                        Toast.makeText(context, url, Toast.LENGTH_SHORT).show()
//                    scope.launch {
//                        val file = downloadImageToFile(context, url)
//                        if (file != null) {
//                            rotateFile(file, true)
//                            val reducedFile = reduceFileImage(file)
//                            val imageMultipart: MultipartBody.Part =
//                                MultipartBody.Part.createFormData(
//                                    "file",
//                                    file.name,
//                                    reducedFile.asRequestBody("image/*".toMediaTypeOrNull())
//                                )
//                            prediction = viewModel.predict(imageMultipart)
//                            when (val res = prediction) {
//                                is UiState.Success -> {
//                                    navController.currentBackStackEntry?.savedStateHandle?.set(
//                                        key = "artResponse",
//                                        value = res.data
//                                    )
//                                    navController.currentBackStackEntry?.savedStateHandle?.set(
//                                        key = "file",
//                                        value = file.path
//                                    )
//                                }
//
//                                is UiState.Error -> {
//                                    val errorMessage = res.errorMessage
//                                    Log.e("HomeScreen", errorMessage)
//                                }
//
//                                is UiState.Loading -> {
//                                    Log.i("HomeScreen", "loading")
//                                }
//                            }
//                        } else {
//                            // Failed to download or save the file
//                            Toast.makeText(context, "Image process failure", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }
                })
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGrid(
    data: List<GalleryResponse>,
    onItemClicked: (url: String) -> Unit,
    modifier: Modifier = Modifier,
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
                AsyncImage(
                    model = img.url,
                    contentDescription = img.type,
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onItemClicked(img.type) }
                )
            }
        }
    }
}
