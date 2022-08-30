package com.example.cmd.kotlin.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cmd.kotlin.Api.ApiProvider
import com.example.cmd.kotlin.Main.SignInActivity2
import com.example.cmd.kotlin.Response.TimetableResponse2
import com.example.cmd.databinding.FragmentTimetableBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class TimetableFragment2 : Fragment() {

    private lateinit var binding: FragmentTimetableBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTimetableBinding.inflate(inflater, container, false)

        val currentdate: Date = Date()

        val calendar: Calendar = Calendar.getInstance()

        val format: SimpleDateFormat = SimpleDateFormat("MM월 dd일" + " " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.KOREA) + "요일")

        val format_time = format.format(calendar.time)

        binding.tvdate.setText(format_time)

        ApiProvider.retrofit.timeTable(SignInActivity2.token, calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)).enqueue(object : Callback<TimetableResponse2>{
            override fun onResponse(
                call: Call<TimetableResponse2>,
                response: Response<TimetableResponse2>
            ) {
                if(response.isSuccessful) {
                    binding.tvperiod01.setText(response.body()!!.period1st)
                    binding.tvperiod02.setText(response.body()!!.period2nd)
                    binding.tvperiod03.setText(response.body()!!.period3th)
                    binding.tvperiod04.setText(response.body()!!.period4th)
                    binding.tvperiod05.setText(response.body()!!.period5th)
                    binding.tvperiod06.setText(response.body()!!.period6th)
                    binding.tvperiod07.setText(response.body()!!.period7th)
                    binding.tvperiod08.setText(response.body()!!.period8th)
                    binding.tvperiod09.setText(response.body()!!.period9th)
                    binding.tvperiod10.setText(response.body()!!.period10th)
                }
            }

            override fun onFailure(call: Call<TimetableResponse2>, t: Throwable) {

            }

        })

        return binding.root
    }
}