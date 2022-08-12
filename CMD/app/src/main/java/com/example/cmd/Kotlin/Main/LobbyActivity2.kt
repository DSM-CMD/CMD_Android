package com.example.cmd.Kotlin.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.R
import androidx.viewpager2.widget.ViewPager2
import com.example.cmd.Fragment.MypageFragment
import com.example.cmd.Fragment.NoticeBoardFragment
import com.example.cmd.Fragment.StudentInfoFragment
import com.example.cmd.Fragment.TimetableFragment
import com.example.cmd.databinding.ActivityLobbyBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class LobbyActivity2 : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding:ActivityLobbyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLobbyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = ViewPagerAdapter(this)

        binding.pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNavigationView.menu.getItem(position).isChecked = true
                }
            }
        )

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem) : Boolean{
        when(item.itemId){
            com.example.cmd.R.id.timetable -> {
                binding.pager.currentItem = 0
                return true
            }
            com.example.cmd.R.id.studentinfo -> {
                binding.pager.currentItem = 1
                return true
            }
            com.example.cmd.R.id.noticeboard -> {
                binding.pager.currentItem = 2
                return true
            }
            com.example.cmd.R.id.mypage -> {
                binding.pager.currentItem = 3
                return true
            }
            else -> return false
        }
    }

}