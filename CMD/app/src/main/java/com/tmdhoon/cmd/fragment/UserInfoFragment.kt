package com.tmdhoon.cmd.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tmdhoon.cmd.R

class UserInfoFragment : Fragment() {

    companion object {
        fun newInstance(): UserInfoFragment {
            return UserInfoFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.activity_mypage_fragment, container, false)
        return view
    }
}