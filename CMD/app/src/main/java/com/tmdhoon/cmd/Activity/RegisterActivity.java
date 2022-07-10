package com.tmdhoon.cmd.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.tmdhoon.cmd.Api.ApiProvider;
import com.tmdhoon.cmd.Api.ServerApi;
import com.tmdhoon.cmd.R;
import com.tmdhoon.cmd.Request.RegisterRequest;
import com.tmdhoon.cmd.Response.RegisterResponse;
import com.tmdhoon.cmd.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private Editable secretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etusername.getText().toString();
                String number = binding.etnumber.getText().toString();
                String userId = binding.etuserId.getText().toString();
                String password = binding.etpassword.getText().toString();
                secretKey = binding.etsecretKey.getText();

                if(username == null)
                    Toast.makeText(RegisterActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(username.length()>20)
                    Toast.makeText( RegisterActivity.this, "이름을 20자 이하로 입력해주세요", Toast.LENGTH_SHORT).show();
                if(number == null)
                    Toast.makeText(RegisterActivity.this, "학번을 정확히 입력해주세요", Toast.LENGTH_SHORT).show();
                if(userId == null)
                    Toast.makeText(RegisterActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(userId.length()>12)
                    Toast.makeText(RegisterActivity.this, "아이디를 12자 이하로 입력해주세요", Toast.LENGTH_SHORT).show();
                if(password == null)
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(password.length()>24)
                    Toast.makeText(RegisterActivity.this, "비밀번호를 24자 이하로 입력해주세요", Toast.LENGTH_SHORT).show();
                if(secretKey == null)
                    Toast.makeText(RegisterActivity.this, "가입코드를 입력해주세요", Toast.LENGTH_SHORT).show();
                else{
                    register();
                }

            }
        });
    }

    public void register(){
        String username = binding.etusername.getText().toString();
        String number = binding.etnumber.getText().toString();
        String userId = binding.etuserId.getText().toString();
        String password = binding.etpassword.getText().toString();
        secretKey = binding.etsecretKey.getText();

        RegisterRequest registerRequest = new RegisterRequest(username, number, userId, password);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);



    }

}