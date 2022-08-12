package com.example.cmd.Kotlin.Main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.cmd.Api.ServerApi
import com.example.cmd.Kotlin.Api.ApiProvider
import com.example.cmd.Kotlin.Api.ServerApi2
import com.example.cmd.Kotlin.Request.SignUpRequest2
import com.example.cmd.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etregisterId.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = binding.etregisterId.text.length
                binding.tvidCount.setText("$length/12")
                if(length <12){
                    binding.tvidCount.setTextColor(Color.parseColor("#ABABAB"))
                }else {
                    binding.tvidCount.setTextColor(Color.RED)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.btRegister.setOnClickListener {
            signUpCheck()
        }

        binding.tvLogin.setOnClickListener{
            val intent = Intent(applicationContext, SignInActivity2::class.java)
            startActivity(intent)
        }

    }

    private fun signUpCheck() {
        val id = binding.etregisterId.text.length
        val pw = binding.etregisterPw.text.length
        val check = binding.etregisterpwcheck.text.length
        val key = binding.etregistersecretKey.text.length

        if(id == 0 && pw == 0 && check == 0 && key == 0){
            Toast.makeText(applicationContext, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
        }else if(binding.etregisterPw.text != binding.etregisterpwcheck.text){
            Toast.makeText(applicationContext, "비밀번호 확인이 다릅니다", Toast.LENGTH_SHORT).show()
        }else{
            signUp()
        }
    }

    private fun signUp() {
        val id = binding.etregisterId.text.toString()
        val pw = binding.etregisterPw.text.toString()
        val check = binding.etregistersecretKey.text.toString()

        val signUpRequest2 = SignUpRequest2(id, pw)

        ApiProvider.retrofit.signUp(check, signUpRequest2).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "회원가입 되었습니다! 로그인 후 이용해주세요", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, SignInActivity2::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

        })

    }
}