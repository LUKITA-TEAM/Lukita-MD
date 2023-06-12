package com.lukitateam.lukita.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukitateam.lukita.R
import com.lukitateam.lukita.ui.components.DefaultHeader
import com.lukitateam.lukita.ui.components.textField.EmailTextField
import com.lukitateam.lukita.ui.components.textField.PasswordTextField
import com.lukitateam.lukita.ui.theme.LukitaTheme
import com.lukitateam.lukita.ui.theme.Primary
import com.lukitateam.lukita.ui.theme.Secondary
import com.lukitateam.lukita.util.comparingPassword
import com.lukitateam.lukita.util.validatePassword

@Composable
fun ProfileScreen() {
    Column {
        ProfileHeader(drawable = R.drawable.wave_ps_above)
        FormField(
            identityField = stringResource(R.string.identity),
            changePasswordField = stringResource(R.string.change_password),
        )
        ButtonProfile(
            textSave = stringResource(R.string.save),
            textLogout = stringResource(R.string.logout),
        )
        ProfileFooter(drawable = R.drawable.wave_ps_bottom)
    }
}

@Composable
fun ProfileHeader(
    drawable: Int,
    modifier: Modifier = Modifier
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(100.dp)
                .background(Primary)
                .padding(vertical = 4.dp, horizontal = 16.dp)
        ) {
            DefaultHeader(
                textHeader = stringResource(R.string.profile_header),
                color = Color.White,
            )
        }
        Image(
            painter = painterResource(drawable),
            contentDescription = stringResource(R.string.wave_desc),
            modifier = modifier.padding(bottom = 16.dp),
        )
    }
}

@Composable
fun FormField(
    identityField: String,
    changePasswordField: String,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var isValidEmail by rememberSaveable {
        mutableStateOf(true)
    }
    var oldPassword by rememberSaveable {
        mutableStateOf("") //checking password lama
    }
    var newPassword by rememberSaveable {
        mutableStateOf("")
    }
    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var isPasswordValid by remember {
        mutableStateOf(true)
    }

    // TODO("Logic Changes Password")
    Column(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Text(
            text = identityField,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(bottom = 6.dp)
        )
        EmailTextField(
            text = email,
            valid = isValidEmail,
            onValueChange = {},
            readOnly = true,
        )
    }
    Column(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Text(
            text = changePasswordField,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(bottom = 6.dp)
        )
        PasswordTextField(
            placeholder = stringResource(R.string.old_password),
            password = oldPassword,
            showPassword = showPassword,
            valid = isPasswordValid,
            onValueChange = { text ->
                isPasswordValid = comparingPassword(oldPassword, text)
            },
            onTrailingIconClicked = {
                showPassword = !showPassword
            }
        )
        PasswordTextField(
            placeholder = stringResource(R.string.new_password),
            password = newPassword,
            showPassword = showPassword,
            valid = isPasswordValid,
            onValueChange = { text ->
                isPasswordValid = validatePassword(text)
            },
            onTrailingIconClicked = {
                showPassword =! showPassword
            }
        )
        PasswordTextField(
            placeholder = stringResource(R.string.confirm_password),
            password = confirmPassword,
            showPassword = showPassword,
            valid = isPasswordValid,
            onValueChange = { text ->
                isPasswordValid = comparingPassword(newPassword, text)
                confirmPassword = text
            },
            onTrailingIconClicked = {
                showPassword =! showPassword
            }
        )
    }
}

@Composable
fun ButtonProfile(
    textSave: String,
    textLogout: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 8.dp)) {
        Button(
            onClick = { TODO("Logic Save Changes Password") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White,
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        ){
            Text(
                text = textSave,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

        }
        Button(
            onClick = { TODO("Logic Logout") },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary,
                contentColor = Color.Black,
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        ){
            Text(
                text = textLogout,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )

        }

    }
}

@Composable
fun ProfileFooter(
    drawable: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(drawable),
                contentDescription = stringResource(R.string.wave_desc),
            )
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .background(Primary)
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileScreenPreview() {
    LukitaTheme {
        ProfileScreen()
    }
}
