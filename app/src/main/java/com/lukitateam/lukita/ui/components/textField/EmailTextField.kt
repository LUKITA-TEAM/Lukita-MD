package com.lukitateam.lukita.ui.components.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    username: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = username,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        onValueChange = { text ->
            onValueChange(text)
        },
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

}

fun validateEmail() {

}