package com.example.cmd.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.databinding.ActivityLoginBinding;
import com.example.cmd.databinding.ActivitySigninBinding;
import com.example.cmd.Request.SignInRequest;
import com.example.cmd.Response.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;
    
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etId.getText().length() == 0)
                    Toast.makeText(SignInActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(binding.etPw.getText().length() == 0){
                    Toast.makeText(SignInActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                else {
                    String id = binding.etId.getText().toString();
                    String pw = binding.etPw.getText().toString();

                    SignInRequest signInRequest = new SignInRequest(id, pw);
                    
                    ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
                    
                    serverApi.signin(signInRequest).enqueue(new Callback<SignInResponse>() {
                        @Override
                        public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                            if(response.isSuccessful()){
                                
                                accessToken = response.body().getAccessToken();
                                
                                Toast.makeText(SignInActivity.this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignInResponse> call, Throwable t) {

                        }
                    });
                    }
                }
            }
        });
    }
}