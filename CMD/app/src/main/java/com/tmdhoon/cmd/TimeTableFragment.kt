package com.tmdhoon.cmd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import android.util.AttributeSet
import android.view.View
import com.tmdhoon.cmd.databinding.ActivityMainBinding
import com.tmdhoon.cmd.databinding.ActivityTimeTableFragmentBinding

class TimeTableFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_fragment)


    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val inflater
        val view = inflater.inflate(R.layout.activity_time_table_fragment, container, false)
        return view
    }
    companion object{
        fun newInstance() : TimeTableFragment{
            return TimeTableFragment()
        }
    }
}