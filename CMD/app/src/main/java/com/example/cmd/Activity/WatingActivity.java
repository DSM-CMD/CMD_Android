package com.example.cmd.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.cmd.databinding.ActivityWatingBinding;

public class WatingActivity extends AppCompatActivity {

    private ActivityWatingBinding binding;
    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 투명도 0~1 까지 설정
        anim = new AlphaAnimation(0.0f, 1.0f);
        // 2초동안 실행
        anim.setDuration(2000);
        // 한 번 실행후 다시 시작까지의 대기 시간
        anim.setStartOffset(20);
        // 다시 실행할 때 Reverse 모드로 실행
        anim.setRepeatMode(Animation.REVERSE);
        // 무제한
        anim.setRepeatCount(Animation.INFINITE);

        binding.tvtouch.startAnimation(anim);

        binding.wating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}