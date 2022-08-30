package com.example.cmd.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.cmd.databinding.ActivitySubjectBinding;
import com.example.cmd.response.SubjectResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectActivity extends Activity {

    private ActivitySubjectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivitySubjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int number = intent.getIntExtra("Number", 0);

        // 수업 정보 조회
        SignInActivity.serverApi.subject(SignInActivity.accessToken, number).enqueue(new Callback<SubjectResponse>() {
            @Override
            public void onResponse(Call<SubjectResponse> call, Response<SubjectResponse> response) {
                if(response.isSuccessful()){
                    binding.tvsubject.setText(response.body().getName());
                    binding.textview1.setText(response.body().getPlace());
                    binding.textview3.setText(response.body().getTeacher());
                }
            }

            @Override
            public void onFailure(Call<SubjectResponse> call, Throwable t) {
            }
        });

        if(number == 0){
            binding.tvsubject.setText("정보없음");
            binding.textview1.setText("정보없음");
            binding.textview3.setText("정보없음");
        }

        // X 이미지 클릭 리스너
        binding.ivNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}