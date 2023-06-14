package com.lukitateam.lukita.ui.screen.camera

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lukitateam.lukita.data.api.ApiConfig
import com.lukitateam.lukita.data.response.ArtResponse
import com.lukitateam.lukita.ui.common.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import okhttp3.MultipartBody

class CameraViewModel : ViewModel() {
    private val _predictState = Channel<CameraState>()
    val predictState = _predictState.receiveAsFlow()

    suspend fun predict(file: MultipartBody.Part): UiState<ArtResponse> {
        return try {
            val response = ApiConfig.apiService.predict(file)
            if (response.isSuccessful) {
                val artResponse = response.body()
                if (artResponse != null) {
                    _predictState.send(CameraState(isSuccess = "Loaded Data Success"))
                    UiState.Success(artResponse)
                } else {
                    _predictState.send(CameraState(isError = "Empty response"))
                    UiState.Error("Empty response")
                }
            } else {
                Log.e("err", "Request failed: ${response.code()}")
                _predictState.send(CameraState(isError = "Request failed: ${response.code()}"))
                UiState.Error("Request failed: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("err", "Request failed: ${e.message}")
            _predictState.send(CameraState(isError = "An error occurred: ${e.message}"))
            UiState.Error("An error occurred: ${e.message}")
        }
    }
}