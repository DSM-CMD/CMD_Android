package com.example.cmd.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.cmd.databinding.ActivityWatingBinding;

public class WatingActivity extends AppCompatActivity {

    private ActivityWatingBinding binding;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(2000);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
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