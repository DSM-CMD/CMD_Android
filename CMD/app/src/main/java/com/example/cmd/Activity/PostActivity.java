package com.example.cmd.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Request.PostRequest;
import com.example.cmd.databinding.ActivityPostBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postCheck();
            }
        });


    }

    private void postCheck() {
        int title = binding.etTitle.getText().length();
        int content = binding.etContent.getText().length();
        
        if(title != 0 && content != 0) post();
        else if(title == 0 && content != 0) Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
        else if(title != 0 && content == 0) Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
        else if(title == 0 && content == 0) Toast.makeText(this, "제목과 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
    }

    private void post() {
        String title = binding.etTitle.getText().toString();
        String content = binding.etContent.getText().toString();

        PostRequest postRequest = new PostRequest(title, content);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.post(SignInActivity.accessToken, postRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PostActivity.this, "정상적으로 등록되었습니다!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}