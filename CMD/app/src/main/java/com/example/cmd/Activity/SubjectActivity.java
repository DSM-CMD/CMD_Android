package com.example.cmd.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Response.SubjectResponse;
import com.example.cmd.databinding.ActivitySubjectBinding;

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

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);
        serverApi.subject(SignInActivity.accessToken, intent.getIntExtra("Number", 0)).enqueue(new Callback<SubjectResponse>() {
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

        binding.ivno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}