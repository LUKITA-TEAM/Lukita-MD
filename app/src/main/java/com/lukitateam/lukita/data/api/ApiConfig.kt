package com.lukitateam.lukita.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://lukita-model-vrer4llm6a-et.a.run.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}