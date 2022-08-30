package com.example.cmd.Kotlin.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    var BASE_URL = "http://54.180.122.62:8080/"

    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServerApi2::class.java)
}