package com.example.cmd.Kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cmd.databinding.ActivityWatingBinding

class WatingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWatingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        

    }
}