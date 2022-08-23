package com.example.cmd.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.R;
import com.example.cmd.Request.SignInRequest;
import com.example.cmd.Response.SignInResponse;
import com.example.cmd.databinding.ActivitySigninBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;
    public static String accessToken;

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = preferences.edit();

        binding.ivvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(preferences.getBoolean("visible", false) == false) {
                    binding.ivvisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.etPw.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    binding.etPw.setSelection(binding.etPw.getText().length());
                    editor.putBoolean("visible", true).commit();
                }else if(preferences.getBoolean("visible", false) == true){
                    binding.ivvisible.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.etPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.etPw.setSelection(binding.etPw.getText().length());
                    editor.putBoolean("visible", false).commit();
                }
            }
        });

        if(preferences.getInt("Check", 0) == 1){
            binding.cbautlLogin.setChecked(true);
            binding.etId.setText(preferences.getString("Id", ""));
            binding.etPw.setText(preferences.getString("Pw", ""));

            String userId = binding.etId.getText().toString();
            String userPw = binding.etPw.getText().toString();

            SignInRequest signInRequest = new SignInRequest(userId, userPw);

            ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

            serverApi.signin(signInRequest).enqueue(new Callback<SignInResponse>() {
                @Override
                public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                    if(response.isSuccessful()){

                        Toast.makeText(SignInActivity.this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show();

                        accessToken = response.body().getAccessToken();


                        if(binding.cbautlLogin.isChecked()){
                            editor.putInt("Check", 1).commit();
                            editor.putString("Id", binding.etId.getText().toString()).commit();
                            editor.putString("Pw", binding.etPw.getText().toString()).commit();
                        }
                        
                        Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<SignInResponse> call, Throwable t) {

                }
            });

        }else binding.cbautlLogin.setChecked(false);

        binding.etId.setSelection(binding.etId.getText().length());

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signinCheck();
            }
        });
    }

    private void signinCheck(){
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

                    if(binding.cbautlLogin.isChecked()){
                        editor.putInt("Check", 1).commit();
                        editor.putString("Id", binding.etId.getText().toString()).commit();
                        editor.putString("Pw", binding.etPw.getText().toString()).commit();
                    }


                    Toast.makeText(SignInActivity.this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
                    finish();
                    startActivity(intent);

                    editor.putString("Id", binding.etId.getText().toString()).commit();
                    editor.putString("Pw", binding.etPw.getText().toString()).commit();

                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "Server is closed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}