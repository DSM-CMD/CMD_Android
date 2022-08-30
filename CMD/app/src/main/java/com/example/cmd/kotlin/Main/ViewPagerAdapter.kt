package com.example.cmd.kotlin.Main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cmd.kotlin.Fragment.MypageFragment2
import com.example.cmd.kotlin.Fragment.NoticeBoardFragment2
import com.example.cmd.kotlin.Fragment.StudentInfoFragment2
import com.example.cmd.kotlin.Fragment.TimetableFragment2

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> TimetableFragment2()
            1 -> StudentInfoFragment2()
            2 -> NoticeBoardFragment2()
            else -> MypageFragment2()
        }
    }

}