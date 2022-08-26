package com.example.cmd.Api;

import com.example.cmd.Request.InfoUpdateRequest;
import com.example.cmd.Request.PostRequest;
import com.example.cmd.Request.SignInRequest;
import com.example.cmd.Request.SignUpRequest;
import com.example.cmd.Response.MyInfoResponse;
import com.example.cmd.Response.NoticeResponse;
import com.example.cmd.Response.SignInResponse;
import com.example.cmd.Response.StudentInfoResponse;
import com.example.cmd.Response.SubjectResponse;
import com.example.cmd.Response.TimetableResponse;
import com.example.cmd.Response.UserInfoResponse;
import com.example.cmd.Response.UserPostResponse;

import java.util.List;

import javax.security.auth.Subject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("user/info/{number}")
    Call<UserInfoResponse> userinfo(
        @Header("Authorization") String accessToken,
        @Path("number") String number
    );

    @GET("user/myInfo")
    Call<MyInfoResponse> myinfo(
        @Header("Authorization") String accessToken
    );

    @PUT("user/myInfoUpdate")
    Call<Void> infoUpdate(
        @Header("Authorization") String accessToken,
        @Body InfoUpdateRequest infoUpdateRequest
    );

    @GET("user/subject/{subjectNumber}")
    Call<SubjectResponse> subject(
            @Header("Authorization") String accessToken,
            @Path("subjectNumber") int subjectNumber
    );

    @GET("user/post")
    Call<List<UserPostResponse>> userpost(
            @Header("Authorization") String accessToken
    );

    @POST("user/post")
    Call<Void> post(
            @Header("Authorization") String accessToken,
            @Body PostRequest postRequest
    );

}
