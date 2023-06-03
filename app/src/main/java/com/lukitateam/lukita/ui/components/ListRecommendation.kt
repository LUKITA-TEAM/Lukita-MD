package com.lukitateam.lukita.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.model.StyleArt
import com.lukitateam.lukita.ui.theme.LukitaTheme

@Composable
fun ListRecommendation(
    styleArt: StyleArt,
    modifier: Modifier = Modifier,
) {
    Row {
        Text(text = stringResource(styleArt.styleArt))
    }
}

@Preview(showBackground = true)
@Composable
fun ListRecommendationPreview() {
    LukitaTheme() {
        ListRecommendation(
            styleArt = StyleArt(R.string.style_art_naturalism),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}