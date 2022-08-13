package com.example.cmd.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.R;
import com.example.cmd.Response.UserInfoResponse;
import com.example.cmd.databinding.ActivityDialogBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogActivity extends Activity {

    private ActivityDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String number = intent.getStringExtra("number");

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.userinfo(SignInActivity.accessToken, number).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if(response.isSuccessful()){
                    binding.tvname.setText(response.body().getUsername());
                    binding.tvName.setText(response.body().getUsername());
                    binding.tvBirth.setText(response.body().getNumber());
                    binding.tvMajor.setText(response.body().getField());
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });

    }
}