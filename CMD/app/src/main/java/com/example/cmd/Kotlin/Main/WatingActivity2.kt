package com.example.cmd.Kotlin.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cmd.databinding.ActivityWatingBinding

class WatingActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityWatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wating.setOnClickListener{
            val intent = Intent(applicationContext, SignInActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}