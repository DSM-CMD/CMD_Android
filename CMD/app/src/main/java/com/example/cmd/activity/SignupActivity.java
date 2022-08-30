package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.R;
import com.example.cmd.databinding.ActivitySignupBinding;
import com.example.cmd.request.SignUpRequest;

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

        // Edittext 입력 변화에 따른 changed 리스너
        binding.etregisterId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.tvidCount.setText(binding.etregisterId.getText().length() + "/12");
                if(binding.etregisterId.getText().length() == 12){
                    binding.tvidCount.setTextColor(Color.RED);
                }else binding.tvidCount.setTextColor(Color.parseColor("#ABABAB"));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // visible 이미지 클릭 리스너
        binding.ivvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean visiblePw = SignInActivity.preferences.getBoolean("visibleregister", false);
                int length = binding.etregisterPw.getText().length();

                if(visiblePw == false) {
                    binding.ivvisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.etregisterPw.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    binding.etregisterPw.setSelection(length);
                    SignInActivity.editor.putBoolean("visibleregister", true).commit();
                }else if(visiblePw == true){
                    binding.ivvisible.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.etregisterPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.etregisterPw.setSelection(length);
                    SignInActivity.editor.putBoolean("visibleregister", false).commit();
                }
            }
        });

        // visible check 이미지 클릭 리스너
        binding.ivvisiblecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean visibleCheck = SignInActivity.preferences.getBoolean("visibleregistercheck", false);
                int length = binding.etregisterpwcheck.getText().length();

                if(visibleCheck == false) {
                    binding.ivvisiblecheck.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.etregisterpwcheck.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    binding.etregisterpwcheck.setSelection(length);
                    SignInActivity.editor.putBoolean("visibleregistercheck", true).commit();
                }else if(visibleCheck == true){
                    binding.ivvisiblecheck.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.etregisterpwcheck.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.etregisterpwcheck.setSelection(length);
                    SignInActivity.editor.putBoolean("visibleregistercheck", false).commit();
                }
            }
        });

        // register 버튼 클릭 리스너
        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = binding.etregisterId.getText().length();
                String pw = binding.etregisterPw.getText().toString();
                int key = binding.etregistersecretKey.getText().length();
                String pwre = binding.etregisterpwcheck.getText().toString();

                if((id == 0 && pw.length() != 0 && key != 0 && pwre.length() != 0) ||
                        (id == 0 && pw.length() == 0 && key == 0 && pwre.length() == 0)) {
                    Toast.makeText(SignupActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(id != 0 && pw.length() == 0 && key != 0 && pwre.length() != 0) {
                    Toast.makeText(SignupActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(id != 0 && pw.length() != 0 && key == 0 && pwre.length() != 0) {
                    Toast.makeText(SignupActivity.this, "가입코드를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(pw.equals(pwre) == false) {
                    Toast.makeText(SignupActivity.this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                } else{
                    signUp();
                }
            }
        });

        // login TextView 클릭 리스너
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void signUp(){
        String userId = binding.etregisterId.getText().toString();
        String password = binding.etregisterPw.getText().toString();
        String secretKey = binding.etregistersecretKey.getText().toString();

        SignUpRequest signupRequest = new SignUpRequest(userId, password);

        SignInActivity.serverApi.signUp(secretKey, signupRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    Toast.makeText(SignupActivity.this, "회원가입 되었습니다! 로그인 후 이용해주세요", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
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