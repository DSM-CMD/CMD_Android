package com.example.cmd.Api;

import com.example.cmd.Request.SignInRequest;
import com.example.cmd.Request.SignUpRequest;
import com.example.cmd.Response.NoticeResponse;
import com.example.cmd.Response.SignInResponse;
import com.example.cmd.Response.StudentInfoResponse;
import com.example.cmd.Response.TimetableResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

    @POST("signup/{secretKey}")
    Call<Void> signup(
        @Path("secretKey") String secretKey,
        @Body SignUpRequest signupRequest
    );

    @POST("signin")
    Call<SignInResponse> signin(
            @Body SignInRequest signInRequest
    );

    @GET("user/timetable/{day}")
    Call<TimetableResponse> timetable(
        @Header("Authorization") String accessToken,
        @Path("day") String day
    );

    @GET("user/Info")
    Call<List<StudentInfoResponse>> studentinfo(
        @Header("Authorization") String accessToken
    );

    @GET("user/noticeBoard")
    Call<List<NoticeResponse>> notice(
        @Header("Authorization") String accessToken
    );
}
