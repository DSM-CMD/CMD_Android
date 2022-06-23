package com.tmdhoon.cmd.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tmdhoon.cmd.R

class NoticeBoardFragment : Fragment() {

    companion object{
        fun newInstance() : NoticeBoardFragment{
            return NoticeBoardFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_notice_board_fragment, container, false)
        return view
    }
}