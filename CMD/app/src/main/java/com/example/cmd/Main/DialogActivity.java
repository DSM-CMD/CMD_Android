package com.example.cmd.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.cmd.R;
import com.example.cmd.databinding.ActivityDialogBinding;

public class DialogActivity extends AppCompatActivity {

    private ActivityDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        Intent intent = getIntent();
//
//        String number = intent.getExtras().getString("number");
//
//        if(number == null) binding.tvNumber.setText("null");
//        Log.d("test", "setText");
//        binding.tvNumber.setText("234234234");

    }
}