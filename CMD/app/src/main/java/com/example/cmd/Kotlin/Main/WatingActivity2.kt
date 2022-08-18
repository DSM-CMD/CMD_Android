package com.example.cmd.Kotlin.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.example.cmd.databinding.ActivityWatingBinding

class WatingActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityWatingBinding
    lateinit var anim:Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 2000
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE

        binding.tvtouch.startAnimation(anim)

        binding.wating.setOnClickListener{
            val intent = Intent(applicationContext, SignInActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}