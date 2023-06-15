package com.lukitateam.lukita.ui.screen.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lukitateam.lukita.R
import com.lukitateam.lukita.data.response.ArtResponse
import com.lukitateam.lukita.ui.components.DefaultHeader
import com.lukitateam.lukita.ui.components.PhotoItem
import com.lukitateam.lukita.ui.theme.Primary

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    sharedState: ArtResponse,
    filepath: String,
    navigateBack: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DefaultHeader(
            navigateBack = {
                navigateBack()
            },
            stringResource(R.string.detail_header),
            Color.Black
        )
        Content(sharedState = sharedState, filepath = filepath)
        RelatedImage(listImage = sharedState.relatedImage)
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    sharedState: ArtResponse,
    filepath: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(450.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier
                .width(360.dp)
        ) {
            AsyncImage(
                model = filepath,
                contentDescription = sharedState.prediction,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .clip(RoundedCornerShape(20.dp)),
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .width(275.dp)
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Primary)
            ) {

                Text(
                    text = sharedState.prediction,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(vertical = 8.dp)
                )
            }
        }
        Text(
            text = sharedState.explanation,
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp, horizontal = 16.dp),
        )
    }
}

@Composable
fun RelatedImage(
    modifier: Modifier = Modifier,
    listImage: List<String>,
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.related_img_header),
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )

        PhotoGrid(data = listImage)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGrid(
    data: List<String>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(data) { img ->
            PhotoItem(
                type = "", photoUrl = img
            )
        }
    }
}