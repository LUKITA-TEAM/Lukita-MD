package com.lukitateam.lukita.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.components.AuthHeader
import com.lukitateam.lukita.ui.components.textField.EmailTextField
import com.lukitateam.lukita.ui.components.textField.PasswordTextField
import com.lukitateam.lukita.ui.theme.LukitaTheme
import com.lukitateam.lukita.ui.theme.Secondary
import com.lukitateam.lukita.util.validateEmail
import com.lukitateam.lukita.util.validatePassword
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    var username by rememberSaveable {
        mutableStateOf("")
    }
    var isEmailValid by remember {
        mutableStateOf(true)
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var isPasswordValid by remember {
        mutableStateOf(true)
    }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.loginState.collectAsState(initial = null)


    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AuthHeader(
            "Login to continue", R.drawable.wave_01
        )
        Divider(
            thickness = 16.dp, color = Color.Transparent
        )
        EmailTextField(
            username, isEmailValid, onValueChange = { text ->
                isEmailValid = validateEmail(text)
                username = text
            }, modifier.padding(32.dp, 8.dp, 32.dp, 0.dp)
        )
        PasswordTextField(
            stringResource(R.string.password), password, showPassword, isPasswordValid, onValueChange = { text ->
            isPasswordValid = validatePassword(text)
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
            onClick = {
                isEmailValid = validateEmail(username)
                isPasswordValid = validatePassword(password)
                if (validateBeforeSubmit(username, password)) {
                    scope.launch {
                        viewModel.loginUser(username, password)
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary,
                contentColor = Color.Black,
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp, 16.dp, 32.dp, 8.dp),
        ) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator(
                    modifier
                        .width(14.dp)
                        .height(14.dp)
                )
            } else {
                Text(
                    "Login",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }
        Text(
            text = "Dont Have an Account",
            modifier = modifier.fillMaxWidth(),
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
                onClick = { /*TODO (Go To Register)*/ },
                modifier = modifier.padding(32.dp, 8.dp, 32.dp, 8.dp)
            )
        }
    }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
            }
        }
    }
    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotBlank() == true) {
                val error = state.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
            }
        }
    }
}

private fun validateBeforeSubmit(email: String, password: String): Boolean {
    if (validateEmail(email) && validatePassword(password)) {
        return true
    }
    return false
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun LoginScreenPreview() {
    LukitaTheme {
        LoginScreen()
    }
}