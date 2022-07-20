package com.example.cmd.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Request.SignInRequest;
import com.example.cmd.Response.SignInResponse;
import com.example.cmd.databinding.ActivitySigninBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;
    public static String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        binding.etId.setSelection(binding.etId.getText().length());

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signinStart();
            }
        });
    }

    private void signinStart(){
        if(binding.etId.getText().length() == 0)
            Toast.makeText(SignInActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
        else if(binding.etPw.getText().length() == 0){
            Toast.makeText(SignInActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else{
            signIn();
        }
    }

    private void signIn(){
        String userId = binding.etId.getText().toString();
        String password = binding.etPw.getText().toString();

        SignInRequest signInRequest = new SignInRequest(userId, password);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.signin(signInRequest).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.isSuccessful()){

                    accessToken = response.body().getAccessToken();

                    Toast.makeText(SignInActivity.this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
                    finish();
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Toast.makeText(SignInActivity.this, t + "", Toast.LENGTH_SHORT).show();
                Toast.makeText(SignInActivity.this, "길근우 서버 내놔", Toast.LENGTH_SHORT).show();
            }
        });
    }
}