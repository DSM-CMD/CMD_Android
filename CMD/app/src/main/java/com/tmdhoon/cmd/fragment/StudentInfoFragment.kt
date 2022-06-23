package com.tmdhoon.cmd.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tmdhoon.cmd.R

class StudentInfoFragment : Fragment() {

    companion object {
        fun newInstance(): StudentInfoFragment {
            return StudentInfoFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_student_info_fragment, container, false)
        return view
    }


}