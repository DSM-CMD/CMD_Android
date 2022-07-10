package com.tmdhoon.cmd.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tmdhoon.cmd.R

class TimeTableFragment : Fragment() {

    companion object {
        fun newInstance(): TimeTableFragment {
            return TimeTableFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_time_table_fragment, container, false)
        return view
    }


}