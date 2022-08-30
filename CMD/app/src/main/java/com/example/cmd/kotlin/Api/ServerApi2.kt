package com.example.cmd.kotlin.Api

import com.example.cmd.kotlin.Request.SignInRequest2
import com.example.cmd.kotlin.Request.SignUpRequest2
import com.example.cmd.kotlin.Response.NoticeResponse2
import com.example.cmd.kotlin.Response.SignInResponse2
import com.example.cmd.kotlin.Response.StudentInfoResponse2
import com.example.cmd.kotlin.Response.TimetableResponse2
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

    @GET("user/noticeBoard")
    fun notice(
        @Header("Authorization") accessToken: String
    ) : Call<List<NoticeResponse2>>

}