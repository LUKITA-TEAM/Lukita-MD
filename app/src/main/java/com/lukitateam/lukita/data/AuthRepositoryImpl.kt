package com.lukitateam.lukita.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.lukitateam.lukita.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun loginUser(email: String, password: String): Flow<UiState<AuthResult>> {
        return flow {
            emit(UiState.Loading)
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(UiState.Success(result))
        }.catch {
            emit(UiState.Error(it.message.toString()))
        }
    }

    override fun registerUser(
        email: String,
        password: String
    ): Flow<UiState<AuthResult>> {
        return flow {
            emit(UiState.Loading)
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(UiState.Success(result))
        }.catch {
            emit(UiState.Error(it.message.toString()))
        }
    }
}