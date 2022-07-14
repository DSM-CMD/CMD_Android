package com.tmdhoon.cmd.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tmdhoon.cmd.Api.ApiProvider;
import com.tmdhoon.cmd.Api.ServerApi;
import com.tmdhoon.cmd.Request.LoginRequest;
import com.tmdhoon.cmd.Response.LoginResponse;
import com.tmdhoon.cmd.databinding.ActivityLoginBinding;
import com.tmdhoon.cmd.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LoginActivity", "onClick");
                ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

                String Id = binding.etId.getText().toString();
                String Pw = binding.etPw.getText().toString();

                LoginRequest loginRequest = new LoginRequest(Id, Pw);
               
                serverApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            Log.d("LoginActivity", "onResponse");
                            if(response.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "onResponse", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d("LoginActivity", "onFailure");
                        Toast.makeText(LoginActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}

