
package com.example.cmd.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cmd.Activity.PostActivity;
import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Activity.SignInActivity;
import com.example.cmd.R;
import com.example.cmd.RecyclerView.NoticeAdapter;
import com.example.cmd.Response.NoticeResponse;
import com.example.cmd.databinding.FragmentNoticeBoardBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeBoardFragment extends Fragment {

    private LinearLayoutManager linearLayoutManager;
    private NoticeAdapter noticeAdapter;
    private FragmentNoticeBoardBinding binding;
    List<NoticeResponse> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNoticeBoardBinding.inflate(inflater, container, false);

        binding.cbpost.setElevation(0);

        list = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(getActivity());

        binding.noticerecyclerview.setLayoutManager(linearLayoutManager);

        noticeAdapter = new NoticeAdapter(list);

        binding.noticerecyclerview.setAdapter(noticeAdapter);

        if(SignInActivity.preferences.getBoolean("Switch", false) == false){
            binding.teacher.setTextColor(Color.WHITE);
            binding.student.setTextColor(Color.parseColor("#676767"));
            binding.textview.setText("공지사항");
            binding.cbpost.setCardBackgroundColor(Color.parseColor("#232323"));
        }else{
            binding.student.setTextColor(Color.WHITE);
            binding.teacher.setTextColor(Color.parseColor("#676767"));
            binding.textview.setText("게시판");
            binding.cbpost.setCardBackgroundColor(Color.WHITE);
        }

        binding.teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.teacher.setTextColor(Color.WHITE);
                binding.student.setTextColor(Color.parseColor("#676767"));
                SignInActivity.editor.putBoolean("Switch", false).commit();
                binding.textview.setText("공지사항");
                binding.cbpost.setCardBackgroundColor(Color.parseColor("#232323"));
            }
        });

        binding.student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.student.setTextColor(Color.WHITE);
                binding.teacher.setTextColor(Color.parseColor("#676767"));
                SignInActivity.editor.putBoolean("Switch", true).commit();
                binding.textview.setText("게시판");
                binding.cbpost.setCardBackgroundColor(Color.WHITE);
            }
        });

        if(SignInActivity.preferences.getBoolean("Switch", false) == true){
            binding.cbpost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "" + SignInActivity.preferences.getBoolean("Switch", false), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), PostActivity.class);
                    startActivity(intent);
                }
            });
        }

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.notice(SignInActivity.accessToken).enqueue(new Callback<List<NoticeResponse>>() {
            @Override
            public void onResponse(Call<List<NoticeResponse>> call, Response<List<NoticeResponse>> response) {
                if (response.isSuccessful()) {
                    list.addAll(response.body());
                    noticeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<NoticeResponse>> call, Throwable t) {
            }
        });

        return binding.getRoot();
    }
}