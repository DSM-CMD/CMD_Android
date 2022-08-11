package com.example.cmd.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cmd.R;
import com.example.cmd.databinding.ActivityDialogBinding;

public class DialogActivity extends AppCompatActivity {

    private ActivityDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}