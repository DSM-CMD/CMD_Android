package com.example.cmd.kotlin.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmd.kotlin.Api.ApiProvider
import com.example.cmd.kotlin.Main.SignInActivity2
import com.example.cmd.kotlin.Recyclerview.NoticeAdapter2
import com.example.cmd.kotlin.Response.NoticeResponse2
import com.example.cmd.databinding.FragmentNoticeBoardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeBoardFragment2 : Fragment() {

    private lateinit var binding: FragmentNoticeBoardBinding
    private lateinit var list: ArrayList<NoticeResponse2>
    private lateinit var noticeAdapter2: NoticeAdapter2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNoticeBoardBinding.inflate(inflater, container, false)

        list = ArrayList()

        binding.noticerecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.noticerecyclerview.adapter = NoticeAdapter2(list)

        ApiProvider.retrofit.notice(SignInActivity2.token).enqueue(object : Callback<List<NoticeResponse2>>{
            override fun onResponse(
                call: Call<List<NoticeResponse2>>,
                response: Response<List<NoticeResponse2>>
            ) {
                if(response.isSuccessful){
                    list.addAll(response.body()!!)
                    noticeAdapter2.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<NoticeResponse2>>, t: Throwable) {

            }

        })

        return binding.root
    }
}