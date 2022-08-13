package com.example.cmd.Kotlin.Api

import com.example.cmd.Kotlin.Request.SignInRequest2
import com.example.cmd.Kotlin.Request.SignUpRequest2
import com.example.cmd.Kotlin.Response.SignInResponse2
import com.example.cmd.Kotlin.Response.StudentInfoResponse2
import com.example.cmd.Kotlin.Response.TimetableResponse2
import com.example.cmd.Request.SignUpRequest
import retrofit2.Call
import retrofit2.http.*

interface ServerApi2 {

    @POST("signup/{secretKey}")
    fun signUp(
        @Path("secretKey") secretKey: String,
        @Body signupRequest: SignUpRequest2
    ) : Call<Void>

    @POST("signin")
    fun signIn(
        @Body signInRequest2: SignInRequest2
    ) : Call<SignInResponse2>

    @GET("user/timetable/{day}")
    fun timeTable(
        @Header("Authorization") accessToken: String,
        @Path("day") day: String
    ) : Call<TimetableResponse2>

    @GET("user/Info")
    fun studentinfo(
        @Header("Authorization") accessToken: String
    ) : Call<List<StudentInfoResponse2>>

}