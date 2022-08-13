package com.example.cmd.Kotlin.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmd.Kotlin.Api.ApiProvider
import com.example.cmd.Kotlin.Main.SignInActivity2
import com.example.cmd.Kotlin.Recyclerview.StudentAdapter2
import com.example.cmd.Kotlin.Response.StudentInfoResponse2
import com.example.cmd.databinding.FragmentStudentinfoBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class StudentInfoFragment2 : Fragment() {

    private lateinit var binding:FragmentStudentinfoBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: List<StudentInfoResponse2>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStudentinfoBinding.inflate(inflater, container, false)

        binding.studentrecyclerview.layoutManager = GridLayoutManager(activity, 3)

        binding.studentrecyclerview.adapter = StudentAdapter2(list)

        return binding.root
    }
}