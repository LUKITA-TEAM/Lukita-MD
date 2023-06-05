package com.lukitateam.lukita.ui.screen.register

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
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val pref: SessionDatastore,
): ViewModel() {

    private val _registerState = Channel<RegisterState>()
    val registerState = _registerState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        authRepository.registerUser(email, password).collect { result ->
            when(result) {
                is UiState.Success -> {
                    generateSession()
                    _registerState.send(RegisterState(isSuccess = "Register Success"))
                }
                is UiState.Loading -> {
                    _registerState.send(RegisterState(isLoading = true))
                }
                is UiState.Error -> {
                    _registerState.send(RegisterState(isError = result.errorMessage))
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