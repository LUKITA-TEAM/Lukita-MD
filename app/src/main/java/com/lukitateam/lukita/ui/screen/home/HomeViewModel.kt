package com.lukitateam.lukita.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lukitateam.lukita.data.api.ApiConfig
import com.lukitateam.lukita.data.datastore.SessionDatastore
import com.lukitateam.lukita.data.response.ArtResponse
import com.lukitateam.lukita.data.response.GalleryResponse
import com.lukitateam.lukita.ui.common.UiState
import com.lukitateam.lukita.ui.screen.camera.CameraState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pref : SessionDatastore
) : ViewModel() {

    private val _galleryState = Channel<HomeState>()
    val galleryState = _galleryState.receiveAsFlow()

    private val _predictState = Channel<CameraState>()
    val predictState = _predictState.receiveAsFlow()

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

    fun getUser(): Flow<String> {
        return pref.getSession()
    }

}