package com.lukitateam.lukita.ui.components.textField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.lukitateam.lukita.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    showPassword: Boolean,
    onValueChange: (String) -> Unit,
    onTrailingIconClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = password,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (!showPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        onValueChange = { text ->
            onValueChange(text)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock, contentDescription = "Lock Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                onTrailingIconClicked()
            }) {
                Icon(
                    painter = painterResource(R.drawable.ic_eye),
                    contentDescription = "Lock Icon"
                )
            }
        },
        label = {
            Text("Password")
        },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
    )
}

fun validatePassword() {

}