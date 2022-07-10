package com.tmdhoon.cmd.Api;

import android.text.Editable;

import com.tmdhoon.cmd.Request.RegisterRequest;
import com.tmdhoon.cmd.Response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServerApi {

    @PUT("user/signup/{secretKey}")
    Call<RegisterResponse> register(
            @Body RegisterRequest registerRequest,
            @Path("secretKey") Editable secretKey
    );


}
