package com.tmdhoon.cmd.RecyclerVeiw

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tmdhoon.cmd.fragment.NoticeBoardFragment
import com.tmdhoon.cmd.fragment.StudentInfoFragment
import com.tmdhoon.cmd.fragment.UserInfoFragment
import com.tmdhoon.cmd.fragment.TimeTableFragment

class ViewPagerAdapter (fragment: FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> TimeTableFragment()
            1 -> NoticeBoardFragment()
            2 -> StudentInfoFragment()
            else -> UserInfoFragment()
        }
    }
}