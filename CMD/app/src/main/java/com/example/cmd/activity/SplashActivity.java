package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.cmd.R;
import com.example.cmd.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), WatingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        }, 2000);
    }
}