package com.lukitateam.lukita.ui.components.textField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lukitateam.lukita.ui.theme.LukitaTheme
import com.lukitateam.lukita.util.validateEmail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    text: String,
    valid: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {

        OutlinedTextField(
            value = text,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            onValueChange = { text ->
                onValueChange(text)
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email, contentDescription = "Email Icon"
                )
            },
            label = {
                Text("Email")
            },
            modifier = modifier
                .fillMaxWidth()
        )

        if (!valid) {
            Text(
                text = "Please fill the right email",
                color = Color.Red,
                fontSize = 10.sp,
                textAlign = TextAlign.End,
                modifier = modifier.fillMaxWidth()
                    .padding(4.dp),
            )
        }
    }

}
