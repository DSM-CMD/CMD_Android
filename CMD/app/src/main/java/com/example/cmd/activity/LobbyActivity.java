package com.example.cmd.Activity;

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

import com.example.cmd.Fragment.MypageFragment;
import com.example.cmd.Fragment.NoticeBoardFragment;
import com.example.cmd.Fragment.StudentInfoFragment;
import com.example.cmd.Fragment.TimetableFragment;
import com.example.cmd.R;
import com.example.cmd.databinding.ActivityLobbyBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LobbyActivity extends AppCompatActivity {

    private ActivityLobbyBinding binding;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private FragmentStateAdapter pagerAdapter;

    private static final int NUM_PAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLobbyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pagerAdapter = new ScreeSlidePagerAdapter(this);
        binding.pager.setAdapter(pagerAdapter);

        binding.pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case R.id.timetable:
                         binding.pager.setCurrentItem(0);
                         break;
                    case R.id.studentinfo:
                         binding.pager.setCurrentItem(1);
                         break;
                    case R.id.noticeboard:
                         binding.pager.setCurrentItem(2);
                         break;
                    case R.id.mypage:
                         binding.pager.setCurrentItem(3);
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
            if (position == 0){
                return new TimetableFragment();
            } else if (position == 1){
                return new StudentInfoFragment();
            } else if (position == 2){
                return new NoticeBoardFragment();
            } else{
                return new MypageFragment();
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}