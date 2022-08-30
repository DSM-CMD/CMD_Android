package com.example.cmd.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cmd.activity.SubjectActivity;
import com.example.cmd.activity.SignInActivity;
import com.example.cmd.R;
import com.example.cmd.databinding.FragmentTimetableBinding;
import com.example.cmd.response.TimetableResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableFragment extends Fragment {

    private FragmentTimetableBinding binding;
    private Calendar calendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTimetableBinding.inflate(getLayoutInflater(), container, false);

        timetable();

        clicklistener();

        binding.tvtimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timetable();
                Toast.makeText(getActivity(), "현재 시간표로 변경되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

        // 전 날 시간표 조회
        binding.ivleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.DATE, -1);
                String format = new SimpleDateFormat("MM월 dd일 " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.KOREA) + "요일").format(calendar.getTime());
                binding.tvdate.setText(format);
                SignInActivity.serverApi.timetable(SignInActivity.accessToken, calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)).enqueue(new Callback<TimetableResponse>() {
                    @Override
                    public void onResponse(Call<TimetableResponse> call, Response<TimetableResponse> response) {
                        if(response.isSuccessful()){
                            binding.tvperiod01.setText(response.body().getPeriod1st());
                            binding.tvperiod02.setText(response.body().getPeriod2nd());
                            binding.tvperiod03.setText(response.body().getPeriod3th());
                            binding.tvperiod04.setText(response.body().getPeriod4th());
                            binding.tvlaunch.setText("점심시간");
                            binding.tvperiod05.setText(response.body().getPeriod5th());
                            binding.tvperiod06.setText(response.body().getPeriod6th());
                            binding.tvperiod07.setText(response.body().getPeriod7th());
                            binding.tvperiod08.setText(response.body().getPeriod8th());
                            binding.tvdinner.setText("저녁시간");
                            binding.tvperiod09.setText(response.body().getPeriod9th());
                            binding.tvperiod10.setText(response.body().getPeriod10th());

                            setperiod();
                        }else if(response.code() == 500){
                            setperiod();
                            notimetable();
                        }
                    }

                    @Override
                    public void onFailure(Call<TimetableResponse> call, Throwable t) {

                    }
                });


            }
        });

        // 다음 날 시간표 조회
        binding.ivright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.DATE, 1);
                String format = new SimpleDateFormat("MM월 dd일 " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.KOREA) + "요일").format(calendar.getTime());
                binding.tvdate.setText(format);
                SignInActivity.serverApi.timetable(SignInActivity.accessToken, calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)).enqueue(new Callback<TimetableResponse>() {
                    @Override
                    public void onResponse(Call<TimetableResponse> call, Response<TimetableResponse> response) {
                        if(response.isSuccessful()){
                            binding.tvperiod01.setText(response.body().getPeriod1st());
                            binding.tvperiod02.setText(response.body().getPeriod2nd());
                            binding.tvperiod03.setText(response.body().getPeriod3th());
                            binding.tvperiod04.setText(response.body().getPeriod4th());
                            binding.tvlaunch.setText("점심시간");
                            binding.tvperiod05.setText(response.body().getPeriod5th());
                            binding.tvperiod06.setText(response.body().getPeriod6th());
                            binding.tvperiod07.setText(response.body().getPeriod7th());
                            binding.tvperiod08.setText(response.body().getPeriod8th());
                            binding.tvdinner.setText("저녁시간");
                            binding.tvperiod09.setText(response.body().getPeriod9th());
                            binding.tvperiod10.setText(response.body().getPeriod10th());

                            setperiod();
                        }else if(response.code() == 500){
                            setperiod();
                            notimetable();
                        }
                    }

                    @Override
                    public void onFailure(Call<TimetableResponse> call, Throwable t) {

                    }
                });
            }
        });

        return binding.getRoot();
    }

    // 1교시 ~ 10교시 텍스트 설정
    private void setperiod() {
        binding.period01.setText("1교시");
        binding.period02.setText("2교시");
        binding.period03.setText("3교시");
        binding.period04.setText("4교시");
        binding.period05.setText("5교시");
        binding.period06.setText("6교시");
        binding.period07.setText("7교시");
        binding.period08.setText("8교시");
        binding.period09.setText("9교시");
        binding.period10.setText("10교시");
    }

    // 시간표 조회 함수
    private void timetable(){
        calendar.clear();
        calendar = Calendar.getInstance();
        String format = new SimpleDateFormat("MM월 dd일 " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.KOREA) + "요일").format(calendar.getTime());
        binding.tvdate.setText(format);
        SignInActivity.serverApi.timetable(SignInActivity.accessToken, calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)).enqueue(new Callback<TimetableResponse>() {
            @Override
            public void onResponse(Call<TimetableResponse> call, Response<TimetableResponse> response) {
                if(response.isSuccessful()){
                    SignInActivity.editor.putBoolean("TimeTable", true).commit();
                    binding.tvperiod01.setText(response.body().getPeriod1st());
                    binding.tvperiod02.setText(response.body().getPeriod2nd());
                    binding.tvperiod03.setText(response.body().getPeriod3th());
                    binding.tvperiod04.setText(response.body().getPeriod4th());
                    binding.tvlaunch.setText("점심시간");
                    binding.tvperiod05.setText(response.body().getPeriod5th());
                    binding.tvperiod06.setText(response.body().getPeriod6th());
                    binding.tvperiod07.setText(response.body().getPeriod7th());
                    binding.tvperiod08.setText(response.body().getPeriod8th());
                    binding.tvdinner.setText("저녁시간");
                    binding.tvperiod09.setText(response.body().getPeriod9th());
                    binding.tvperiod10.setText(response.body().getPeriod10th());

                    setperiod();
                }
                else if(response.code() == 500){
                    notimetable();
                    setperiod();
                }
            }

            @Override
            public void onFailure(Call<TimetableResponse> call, Throwable t) {
            }
        });
    }

    // 시간표가 없을때
    private void notimetable(){
        binding.tvperiod01.setText("시");
        binding.tvperiod02.setText("간");
        binding.tvperiod03.setText("표");
        binding.tvperiod04.setText("가");
        binding.tvlaunch.setText("점심시간");
        binding.tvperiod05.setText("없");
        binding.tvperiod06.setText("어");
        binding.tvperiod07.setText("용");
        binding.tvperiod08.setText("!");
        binding.tvdinner.setText("저녁시간");
        binding.tvperiod09.setText("!");
        binding.tvperiod10.setText("!");
    }

    // 시간표 클릭 리스너
    private void clicklistener() {
        binding.cvperiod01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                String period01 = binding.tvperiod01.getText().toString();

                switch(period01) {
                    case "국어" : number = 1; break;
                    case "수학" : number = 2; break;
                    case "사회" : number = 3; break;
                    case "과학" : number = 4; break;
                    case "영어" : number = 5; break;
                    case "음악" : number = 6; break;
                    case "C++" : number = 7; break;
                    case "자료구조" : number = 8; break;
                    case "체육" : number =  9; break;
                    case "창체" : number =  10; break;
                }
                Intent intent = new Intent(getActivity(), SubjectActivity.class);
                intent.putExtra("Number", number);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
        binding.cvperiod02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                switch(binding.tvperiod02.getText().toString()) {
                    case "국어" : number = 1; break;
                    case "수학" : number = 2; break;
                    case "사회" : number = 3; break;
                    case "과학" : number = 4; break;
                    case "영어" : number = 5; break;
                    case "음악" : number = 6; break;
                    case "C++" : number = 7; break;
                    case "자료구조" : number = 8; break;
                    case "체육" : number = 9; break;
                    case "창체" : number = 10; break;
                }
                Intent intent = new Intent(getActivity(), SubjectActivity.class);
                intent.putExtra("Number", number);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        });
        binding.cvperiod03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                switch(binding.tvperiod03.getText().toString()) {
                    case "국어" : number = 1; break;
                    case "수학" : number = 2; break;
                    case "사회" : number = 3; break;
                    case "과학" : number = 4; break;
                    case "영어" : number = 5; break;
                    case "음악" : number = 6; break;
                    case "C++" : number = 7; break;
                    case "자료구조" : number = 8; break;
                    case "체육" : number = 9; break;
                    case "창체" : number = 10; break;
                }
                Intent intent = new Intent(getActivity(), SubjectActivity.class);
                intent.putExtra("Number", number);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
        binding.cvperiod04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                switch(binding.tvperiod04.getText().toString()) {
                    case "국어" : number = 1; break;
                    case "수학" : number = 2; break;
                    case "사회" : number = 3; break;
                    case "과학" : number = 4; break;
                    case "영어" : number = 5; break;
                    case "음악" : number = 6; break;
                    case "C++" : number = 7; break;
                    case "자료구조" : number = 8; break;
                    case "체육" : number = 9; break;
                    case "창체" : number = 10; break;
                }
                Intent intent = new Intent(getActivity(), SubjectActivity.class);
                intent.putExtra("Number", number);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
        binding.cvperiod05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                switch(binding.tvperiod05.getText().toString()) {
                    case "국어" : number = 1; break;
                    case "수학" : number = 2; break;
                    case "사회" : number = 3; break;
                    case "과학" : number = 4; break;
                    case "영어" : number = 5; break;
                    case "음악" : number = 6; break;
                    case "C++" : number = 7; break;
                    case "자료구조" : number = 8; break;
                    case "체육" : number = 9; break;
                    case "창체" : number = 10; break;
                }
                Intent intent = new Intent(getActivity(), SubjectActivity.class);
                intent.putExtra("Number", number);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
        binding.cvperiod06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                switch(binding.tvperiod06.getText().toString()) {
                    case "국어" : number = 1; break;
                    case "수학" : number = 2; break;
                    case "사회" : number = 3; break;
                    case "과학" : number = 4; break;
                    case "영어" : number = 5; break;
                    case "음악" : number = 6; break;
                    case "C++" : number = 7; break;
                    case "자료구조" : number = 8; break;
                    case "체육" : number = 9; break;
                    case "창체" : number = 10; break;
                }
                if(binding.tvperiod06.getText().equals("전공동아리") == false){
                    Intent intent = new Intent(getActivity(), SubjectActivity.class);
                    intent.putExtra("Number", number);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            }
        });
        binding.cvperiod07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = 0;
                switch(binding.tvperiod07.getText().toString()) {
                    case "국어" : number = 1; break;
                    case "수학" : number = 2; break;
                    case "사회" : number = 3; break;
                    case "과학" : number = 4; break;
                    case "영어" : number = 5; break;
                    case "음악" : number = 6; break;
                    case "C++" : number = 7; break;
                    case "자료구조" : number = 8; break;
                    case "체육" : number = 9; break;
                    case "창체" : number = 10; break;
                }
                if(binding.tvperiod07.getText().equals("전공동아리") == false){
                    Intent intent = new Intent(getActivity(), SubjectActivity.class);
                    intent.putExtra("Number", number);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            }
        });
    }

}