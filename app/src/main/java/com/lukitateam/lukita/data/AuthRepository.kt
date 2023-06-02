package com.lukitateam.lukita.data

import com.google.firebase.auth.AuthResult
import com.lukitateam.lukita.ui.common.UiState
import kotlinx.coroutines.flow.Flow

interface AuthRepository{
    fun loginUser(email: String, password: String) : Flow<UiState<AuthResult>>
    fun registerUser(email: String, password: String) : Flow<UiState<AuthResult>>
}
