package com.tmdhoon.cmd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tmdhoon.cmd.databinding.ActivityMainBinding
import com.tmdhoon.cmd.fragment.NoticeBoardFragment
import com.tmdhoon.cmd.fragment.StudentInfoFragment
import com.tmdhoon.cmd.fragment.UserInfoFragment
import com.tmdhoon.cmd.fragment.TimeTableFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = ViewPagerAdapter(this)

        binding.pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomnav.menu.getItem(position).isChecked = true
                }
            }
        )

        binding.bottomnav.setOnNavigationItemSelectedListener(onBottomNavigationSelectedListener)
    }

//    override fun onResume() {
//        super.onResume()
//        supportFragmentManager.beginTransaction().replace(R.id.framelayout, TimeTableFragment())
//            .commit()
//    }

    private val onBottomNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.timetable -> {
                binding.pager.currentItem = 0
            }
            R.id.noticeboard -> {
                binding.pager.currentItem = 1
            }
            R.id.studentinfo -> {
                binding.pager.currentItem = 2
            }
            R.id.mypage -> {
                binding.pager.currentItem = 3
            }
        }
        true
    }
}
