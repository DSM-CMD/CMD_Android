package com.example.cmd.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
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

        changeColor();

        Intent intent = getIntent();
        String number = intent.getStringExtra("number");

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.userinfo(SignInActivity.accessToken, number).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if(response.isSuccessful()){
                    binding.tvname.setText(response.body().getUsername());
                    binding.tvName.setText(response.body().getUsername());
                    binding.tvNumber.setText(response.body().getNumber());
                    binding.tvBirth.setText(response.body().getBirthday());
                    binding.tvMajor.setText(response.body().getField());

                    if(response.body().getSeatNumber() != null){
                        if(response.body().getSeatNumber() == 1) binding.position1.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 2) binding.position2.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 3) binding.position3.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 4) binding.position4.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 5) binding.position5.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 6) binding.position6.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 7) binding.position7.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 8) binding.position8.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 9) binding.position9.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 10) binding.position10.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 11) binding.position11.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 12) binding.position12.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 13) binding.position13.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 14) binding.position14.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 15) binding.position15.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 16) binding.position16.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 17) binding.position17.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                        if(response.body().getSeatNumber() == 18) binding.position18.setCardBackgroundColor(Color.parseColor("#D9D9D9"));
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });


    }

    private void changeColor() {
        binding.position1.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position2.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position3.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position4.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position5.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position6.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position7.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position8.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position9.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position10.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position11.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position12.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position13.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position14.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position15.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position16.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position17.setCardBackgroundColor(Color.parseColor("#585858"));
        binding.position18.setCardBackgroundColor(Color.parseColor("#585858"));
    }
}