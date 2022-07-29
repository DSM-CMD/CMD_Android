package com.example.cmd.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Request.SignupRequest;
import com.example.cmd.databinding.ActivitySignupBinding;

import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etregisterId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.tvidCount.setText(binding.etregisterId.getText().length() + "/12");
                if(binding.etregisterId.getText().length() == 12){
                    binding.tvidCount.setTextColor(Color.RED);
                }else binding.tvidCount.setTextColor(Color.BLACK);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.etregisterId.getText().length() == 0)
                    Toast.makeText(SignupActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(binding.etregisterPw.getText().length() == 0)
                    Toast.makeText(SignupActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(binding.etregistersecretKey.getText().length() == 0)
                    Toast.makeText(SignupActivity.this, "가입코드를 입력해주세요", Toast.LENGTH_SHORT).show();
                else{
                    signUP();
                }
            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void signUP(){
        String userId = binding.etregisterId.getText().toString();
        String password = binding.etregisterPw.getText().toString();
        String secretKey = binding.etregistersecretKey.getText().toString();

        SignupRequest signupRequest = new SignupRequest(userId, password);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.signup(secretKey, signupRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    Toast.makeText(SignupActivity.this, "회원가입 되었습니다! 로그인 후 이용해주세요", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
                    startActivity(intent);
                    finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "관리자에게 문의해주세요", Toast.LENGTH_SHORT).show();
            }
        });
    }
}