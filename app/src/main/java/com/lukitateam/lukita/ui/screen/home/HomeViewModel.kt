package com.lukitateam.lukita.ui.screen.home

import androidx.lifecycle.ViewModel
import com.lukitateam.lukita.data.api.ApiConfig
import com.lukitateam.lukita.data.response.GalleryResponse
import com.lukitateam.lukita.ui.common.UiState
import com.lukitateam.lukita.ui.screen.login.LoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class HomeViewModel : ViewModel() {

    private val _galleryState = Channel<HomeState>()
    val galleryState = _galleryState.receiveAsFlow()

    suspend fun getGallery(): UiState<List<GalleryResponse>> {
        return try {
            val response = ApiConfig.apiService.gallery()
            if (response.isSuccessful) {
                val galleryResponse = response.body()
                if (galleryResponse != null) {
                    _galleryState.send(HomeState(isSuccess = "Loaded Data Success"))
                    UiState.Success(galleryResponse)
                } else {
                    _galleryState.send(HomeState(isError = "Empty response"))
                    UiState.Error("Empty response")
                }
            } else {
                _galleryState.send(HomeState(isError = "Request failed: ${response.code()}"))
                UiState.Error("Request failed: ${response.code()}")
            }
        } catch (e: Exception) {
            _galleryState.send(HomeState(isError = "An error occurred: ${e.message}"))
            UiState.Error("An error occurred: ${e.message}")
        }
    }

}