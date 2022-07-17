package com.example.cmd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;

import com.example.cmd.databinding.ActivityLobbyBinding;
import com.example.cmd.fragment.MypageFragment;
import com.example.cmd.fragment.NoticeBoardFragment;
import com.example.cmd.fragment.StudentInfoFragment;
import com.example.cmd.fragment.TimetableFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LobbyActivity extends AppCompatActivity {

    private ActivityLobbyBinding binding;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private TimetableFragment timetableFragment = new TimetableFragment();
    private StudentInfoFragment studentInfoFragment = new StudentInfoFragment();
    private NoticeBoardFragment noticeBoardFragment = new NoticeBoardFragment();
    private MypageFragment mypageFragment = new MypageFragment();

    private FragmentStateAdapter pagerAdapter;

    private static final int NUM_PAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pagerAdapter = new ScreeSlidePagerAdapter(this);
        binding.pager.setAdapter(pagerAdapter);


        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, timetableFragment).commitNowAllowingStateLoss();

        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case R.id.timetable:
                        transaction.replace(R.id.framelayout, timetableFragment).commitAllowingStateLoss();
                        break;
                    case R.id.studentinfo:
                        transaction.replace(R.id.framelayout, studentInfoFragment).commitAllowingStateLoss();
                        break;
                    case R.id.noticeboard:
                        transaction.replace(R.id.framelayout, noticeBoardFragment).commitAllowingStateLoss();
                        break;
                    case R.id.mypage:
                        transaction.replace(R.id.framelayout, mypageFragment).commitAllowingStateLoss();
                        break;

                }
                return true;
            }
        });
    }

    private class ScreeSlidePagerAdapter extends FragmentStateAdapter {
        public ScreeSlidePagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) return new TimetableFragment();
            else if (position == 1) return new StudentInfoFragment();
            else if (position == 2) return new NoticeBoardFragment();
            else return new MypageFragment();
        }


        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}