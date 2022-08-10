
package com.example.cmd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Main.SignInActivity;
import com.example.cmd.R;
import com.example.cmd.RecyclerView.NoticeAdapter;
import com.example.cmd.Response.NoticeResponse;
import com.example.cmd.Response.StudentInfoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeBoardFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private NoticeAdapter noticeAdapter;
    List<NoticeResponse> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_notice_board, container, false);

        list = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.noticerecyclerview);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        noticeAdapter = new NoticeAdapter(list);

        recyclerView.setAdapter(noticeAdapter);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

       serverApi.notice(SignInActivity.accessToken).enqueue(new Callback<List<NoticeResponse>>() {
           @Override
           public void onResponse(Call<List<NoticeResponse>> call, Response<List<NoticeResponse>> response) {
               if(response.isSuccessful()){
                   list.addAll(response.body());
                   noticeAdapter.notifyDataSetChanged();
               }
           }

           @Override
           public void onFailure(Call<List<NoticeResponse>> call, Throwable t) {

           }
       });

        return rootView;
    }
}