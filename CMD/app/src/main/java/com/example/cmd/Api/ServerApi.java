package com.example.cmd.Api;

import com.example.cmd.Request.SignInRequest;
import com.example.cmd.Request.SignupRequest;
import com.example.cmd.Response.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

    @POST("users/signup{secretKey}")
    Call<Void> signup(
        @Path("secretKey") String secretKey,
        @Body SignupRequest signupRequest
    );

    @POST("signin")
    Call<SignInResponse> signin(
            @Body SignInRequest signInRequest
    );



}
