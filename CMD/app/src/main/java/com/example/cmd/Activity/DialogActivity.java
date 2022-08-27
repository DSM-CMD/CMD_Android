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

        binding.ivno.setOnClickListener(new View.OnClickListener() {
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

                    long seatnumber = response.body().getSeatNumber();
                    int color = Color.parseColor("#D9D9D9");
                    switch ((int) seatnumber) {
                        case 1: binding.position1.setBackgroundColor(color);break;
                        case 2: binding.position2.setBackgroundColor(color);break;
                        case 3: binding.position3.setBackgroundColor(color);break;
                        case 4: binding.position4.setBackgroundColor(color);break;
                        case 5: binding.position5.setBackgroundColor(color);break;
                        case 6: binding.position6.setBackgroundColor(color);break;
                        case 7: binding.position7.setBackgroundColor(color);break;
                        case 8: binding.position8.setBackgroundColor(color);break;
                        case 9: binding.position9.setBackgroundColor(color);break;
                        case 10: binding.position10.setBackgroundColor(color);break;
                        case 11: binding.position11.setBackgroundColor(color);break;
                        case 12: binding.position12.setBackgroundColor(color);break;
                        case 13: binding.position13.setBackgroundColor(color);break;
                        case 14: binding.position14.setBackgroundColor(color);break;
                        case 15: binding.position15.setBackgroundColor(color);break;
                        case 16: binding.position16.setBackgroundColor(color);break;
                        case 17: binding.position17.setBackgroundColor(color);break;
                        case 18: binding.position18.setBackgroundColor(color);break;

                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });


    }
}