package com.example.cmd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Main.SignInActivity;
import com.example.cmd.R;
import com.example.cmd.RecyclerView.StudentAdapter;
import com.example.cmd.Response.StudentInfoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentInfoFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private StudentAdapter studentAdapter;
    List<StudentInfoResponse> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_studentinfo, container, false);

        list = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.studentrecyclerview);
        gridLayoutManager= new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        studentAdapter = new StudentAdapter(list);

        recyclerView.setAdapter(studentAdapter);

        list.add(new StudentInfoResponse("sleifh", "1234"));

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.studentinfo(SignInActivity.accessToken).enqueue(new Callback<List<StudentInfoResponse>>() {
            @Override
            public void onResponse(Call<List<StudentInfoResponse>> call, Response<List<StudentInfoResponse>> response) {
                if(response.isSuccessful()){
                    list.addAll(response.body());
                    studentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<StudentInfoResponse>> call, Throwable t) {
            }
        });

        return rootView;
    }
}