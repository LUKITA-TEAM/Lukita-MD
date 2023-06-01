package com.lukitateam.lukita.ui.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.components.AuthHeader
import com.lukitateam.lukita.ui.components.textField.EmailTextField
import com.lukitateam.lukita.ui.components.textField.PasswordTextField
import com.lukitateam.lukita.ui.theme.LukitaTheme
import com.lukitateam.lukita.ui.theme.Secondary

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    Column(
        modifier.fillMaxWidth()
    ) {
        AuthHeader(
            "Login to continue", R.drawable.wave_01
        )
        Divider(
            thickness = 16.dp, color = Color.Transparent
        )
        EmailTextField(
            username, onValueChange = { text ->
                username = text
            }, modifier.padding(32.dp, 8.dp, 32.dp, 0.dp)
        )
        PasswordTextField(password, showPassword, onValueChange = { text ->
            password = text
        }, onTrailingIconClicked = {
            showPassword = !showPassword
        }, modifier.padding(32.dp, 8.dp, 32.dp, 0.dp)
        )
        Box(
            modifier = Modifier.fillMaxWidth(), Alignment.BottomEnd
        ) {
            ClickableText(
                AnnotatedString(
                    "Forgot Password",
                ),
                onClick = { /*TODO (Go To Forgot Password)*/ },
                modifier = modifier.padding(32.dp, 8.dp, 32.dp, 0.dp)
            )
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary,
                contentColor = Color.Black,
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp, 16.dp, 32.dp, 8.dp),
        ) {
            Text(
                "Login",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
        Text(
            text = "Dont Have an Account",
            modifier = modifier.
                    fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier.fillMaxWidth(), Alignment.Center
        ) {
            ClickableText(
                AnnotatedString(
                    "Register",
                ),
                style = TextStyle.Default.copy(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                ),
                onClick = { /*TODO (Go To Forgot Password)*/ },
                modifier = modifier.padding(32.dp, 8.dp, 32.dp, 8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun LoginScreenPreview() {
    LukitaTheme {
        LoginScreen()
    }
}