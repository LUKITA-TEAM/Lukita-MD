package com.lukitateam.lukita.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.theme.LukitaTheme
import com.lukitateam.lukita.ui.theme.UclaBlue100

@Composable
fun DefaultHeader(
    navigateBack: () -> Unit,
    textHeader: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .background(UclaBlue100)
                .size(45.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    navigateBack()
                },
        ) {
            Icon(
                painter = painterResource(R.drawable.v_arrow_back),
                contentDescription = stringResource(R.string.v_desc_arrow_back),
            )
        }
        Text(
            text = textHeader,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = color,

            )
    }
}