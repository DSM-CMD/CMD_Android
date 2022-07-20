package com.example.cmd.Api;

import com.example.cmd.Request.SignInRequest;
import com.example.cmd.Request.SignupRequest;
import com.example.cmd.Response.SignInResponse;
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
        @Body SignupRequest signupRequest
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



}
