package com.lukitateam.lukita.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukitateam.lukita.data.AuthRepository
import com.lukitateam.lukita.data.datastore.SessionDatastore
import com.lukitateam.lukita.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val pref: SessionDatastore,
) : ViewModel() {

    private val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        authRepository.loginUser(email, password).collect { result ->
            when (result) {
                is UiState.Success -> {
                    generateSession()
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

    fun getSession(): Flow<String> {
        return pref.getSession()
    }

    private suspend fun generateSession() {
        pref.generateSession()
    }

}