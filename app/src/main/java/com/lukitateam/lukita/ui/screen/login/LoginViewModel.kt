package com.lukitateam.lukita.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukitateam.lukita.data.AuthRepository
import com.lukitateam.lukita.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        authRepository.loginUser(email, password).collect { result ->
            when(result) {
                is UiState.Success -> {
                    _loginState.send(LoginState(isSuccess = "Login Success"))
                }
                is UiState.Loading -> {
                    _loginState.send(LoginState(isLoading = true))
                }
                is UiState.Error -> {
                    _loginState.send(LoginState(isError = result.errorMessage))
                }
            }
        }
    }

}