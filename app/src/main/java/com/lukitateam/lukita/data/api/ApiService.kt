package com.lukitateam.lukita.data.api

import com.lukitateam.lukita.data.response.ArtResponse
import com.lukitateam.lukita.data.response.GalleryResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("/")
    suspend fun predict(
        @Part file: MultipartBody.Part
    ): Response<ArtResponse>

    @GET("/galeri")
    suspend fun gallery(): Response<List<GalleryResponse>>

}