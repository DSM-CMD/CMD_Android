
package com.example.cmd.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cmd.Activity.PostActivity;
import com.example.cmd.Activity.SignInActivity;
import com.example.cmd.R;
import com.example.cmd.Adapter.NoticeAdapter;
import com.example.cmd.Adapter.PostAdapter;
import com.example.cmd.Response.NoticeResponse;
import com.example.cmd.Response.UserPostResponse;
import com.example.cmd.databinding.FragmentNoticeBoardBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeBoardFragment extends Fragment {

    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private NoticeAdapter noticeAdapter;
    private PostAdapter postAdapter;
    private FragmentNoticeBoardBinding binding;
    List<NoticeResponse> noticelist = new ArrayList<>();
    List<UserPostResponse> userpostlist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNoticeBoardBinding.inflate(inflater, container, false);

        binding.cbpost.setElevation(0);

        binding.noticerecyclerview.setLayoutManager(linearLayoutManager);

        setAuth();

        binding.refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(auth() == false){
                    notice();
                    binding.refreshlayout.setRefreshing(false);
                }else{
                    userpost();
                    binding.refreshlayout.setRefreshing(false);
                }
            }
        });

        // Teacher TextView 클릭 리스너
        binding.teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auth() == true) {
                    binding.teacher.setTextColor(Color.WHITE);
                    binding.student.setTextColor(Color.parseColor("#676767"));
                    SignInActivity.editor.putBoolean("Switch", false).commit();
                    binding.textview.setText("공지사항");
                    userpostlist.clear();
                    notice();
                }
            }
        });

        // Student TextView 클릭 리스너
        binding.student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auth() == false) {
                    binding.student.setTextColor(Color.WHITE);
                    binding.teacher.setTextColor(Color.parseColor("#676767"));
                    SignInActivity.editor.putBoolean("Switch", true).commit();
                    binding.textview.setText("게시판");
                    noticelist.clear();
                    userpost();
                }
            }
        });

        // post 클릭 리스너
        binding.cbpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        return binding.getRoot();
    }

    // 초기 교사 / 학생 설정
    private void setAuth() {
        if(auth() == false){
            binding.teacher.setTextColor(Color.WHITE);
            binding.student.setTextColor(Color.parseColor("#676767"));
            binding.textview.setText("공지사항");
            notice();
        }else{
            binding.student.setTextColor(Color.WHITE);
            binding.teacher.setTextColor(Color.parseColor("#676767"));
            binding.textview.setText("게시판");
            userpost();
        }
    }

    // 공지사항 조회 메서드
    private void notice(){
        noticeAdapter = new NoticeAdapter(noticelist);
        binding.noticerecyclerview.setAdapter(noticeAdapter);
        SignInActivity.serverApi.notice(SignInActivity.accessToken).enqueue(new Callback<List<NoticeResponse>>() {
            @Override
            public void onResponse(Call<List<NoticeResponse>> call, Response<List<NoticeResponse>> response) {
                if (response.isSuccessful()) {
                    noticelist.clear();
                    noticelist.addAll(response.body());
                    noticeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<NoticeResponse>> call, Throwable t) {
            }
        });
    }

    // 유저 게시글 조회 메서드
    private void userpost(){
        postAdapter = new PostAdapter(userpostlist);
        binding.noticerecyclerview.setAdapter(postAdapter);
        SignInActivity.serverApi.userPost(SignInActivity.accessToken).enqueue(new Callback<List<UserPostResponse>>() {
            @Override
            public void onResponse(Call<List<UserPostResponse>> call, Response<List<UserPostResponse>> response) {
                if(response.isSuccessful()){
                    userpostlist.clear();
                    userpostlist.addAll(response.body());
                    postAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<UserPostResponse>> call, Throwable t) {
            }
        });
    }

    // preferences 값 조회
    private boolean auth(){
        boolean auth = SignInActivity.preferences.getBoolean("Switch", false);
        return auth;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(auth() == true){
            userpost();
        }else{
            notice();
        }
    }
}