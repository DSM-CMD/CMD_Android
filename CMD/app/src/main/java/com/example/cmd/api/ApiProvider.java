package com.example.cmd.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://10.156.147.133:3000";

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
