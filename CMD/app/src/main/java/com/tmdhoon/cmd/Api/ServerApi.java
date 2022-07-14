package com.tmdhoon.cmd.Api;

import android.text.Editable;

import com.tmdhoon.cmd.Request.LoginRequest;
import com.tmdhoon.cmd.Request.RegisterRequest;
import com.tmdhoon.cmd.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

    @POST("signup/{secretKey}")
    Call<Void> register(
            @Path("secretKey") String secretKey,
            @Body RegisterRequest registerRequest
    );

    @POST("signin")
    Call<LoginResponse> login(
            @Body LoginRequest loginRequest
    );


}
