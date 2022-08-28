package com.example.cmd.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

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

        binding.ivNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String number = intent.getStringExtra("number");

        SignInActivity.serverApi.userInfo(SignInActivity.accessToken, number).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()) {
                    binding.tvname.setText(response.body().getUsername());
                    binding.tvName.setText(response.body().getUsername());
                    binding.tvNumber.setText(response.body().getNumber());
                    binding.tvBirth.setText(response.body().getBirthday());
                    binding.tvMajor.setText(response.body().getField());


                    int color = Color.parseColor("#D9D9D9");
                    if(response.body().getSeatNumber() != null){
                        if(response.body().getSeatNumber() == 1) binding.position1.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 2) binding.position2.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 3) binding.position3.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 4) binding.position4.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 5) binding.position5.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 6) binding.position6.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 7) binding.position7.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 8) binding.position8.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 9) binding.position9.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 10) binding.position10.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 11) binding.position11.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 12) binding.position12.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 13) binding.position13.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 14) binding.position14.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 15) binding.position15.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 16) binding.position16.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 17) binding.position17.setCardBackgroundColor(color);
                        if(response.body().getSeatNumber() == 18) binding.position18.setCardBackgroundColor(color);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });


    }
}