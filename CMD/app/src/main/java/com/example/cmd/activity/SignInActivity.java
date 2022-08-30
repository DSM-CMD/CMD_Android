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

    public static ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

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

        // 자동 로그인 시 설정
        if(preferences.getBoolean("Check", false) == true){
            setAutoLogin();
            binding.cbautlLogin.setChecked(true);
            binding.etId.setText(preferences.getString("Id", ""));
            binding.etPw.setText(preferences.getString("Pw", ""));
        }

        // visible 이미지 클릭 리스너
        binding.ivvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean visible = preferences.getBoolean("visible", false);
                int length = binding.etPw.length();

                if(visible == false) {
                    binding.ivvisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.etPw.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    binding.etPw.setSelection(length);
                    editor.putBoolean("visible", true).commit();
                }else if(visible == true) {
                    binding.ivvisible.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.etPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.etPw.setSelection(length);
                    editor.putBoolean("visible", false).commit();
                }
            }
        });

        // textview 클릭 리스너
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        // 로그인 버튼 클릭 리스너
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn(){
        String userId = binding.etId.getText().toString();
        String userPw = binding.etPw.getText().toString();

        if((userId.length() == 0 && userPw.length() !=0) ||
                (userId.length() == 0 && userPw.length() == 0)){
            Toast.makeText(SignInActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if(userId.length() != 0 && userPw.length() == 0){
            Toast.makeText(SignInActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        }  else {
            SignInRequest signInRequest = new SignInRequest(userId, userPw);

            serverApi.signIn(signInRequest).enqueue(new Callback<SignInResponse>() {
                @Override
                public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                    if(response.isSuccessful()){

                        accessToken = "Bearer " + response.body().getAccessToken();

                        if(binding.cbautlLogin.isChecked()){
                            editor.putBoolean("Check", true).commit();
                        }

                        editor.putString("Id", userId).commit();
                        editor.putString("Pw", userPw).commit();

                        Toast.makeText(SignInActivity.this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<SignInResponse> call, Throwable t) {
                    Toast.makeText(SignInActivity.this, "관리자에게 문의해주세요", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setAutoLogin(){
        String userId = preferences.getString("Id", "");
        String userPw = preferences.getString("Pw", "");

        SignInRequest signInRequest = new SignInRequest(userId, userPw);

        serverApi.signIn(signInRequest).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.isSuccessful()){

                    accessToken = "Bearer " + response.body().getAccessToken();

                    if(binding.cbautlLogin.isChecked()){
                        editor.putBoolean("Check", true).commit();
                        editor.putString("Id", userId).commit();
                        editor.putString("Pw", userPw).commit();
                    }

                    Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();

                    Toast.makeText(SignInActivity.this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {

            }
        });
    }
}