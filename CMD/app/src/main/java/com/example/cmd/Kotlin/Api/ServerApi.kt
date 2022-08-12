package com.example.cmd.Kotlin.Api

import com.example.cmd.Request.SignupRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import javax.crypto.SecretKey

interface ServerApi {

    @POST("signup/{secretKey}")
    fun signUp(
        @Path("secretKey") secretKey: String,
        @Body signupRequest: SignupRequest
    ) : Call<Void>



}